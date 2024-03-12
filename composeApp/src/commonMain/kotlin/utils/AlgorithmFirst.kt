package utils

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt


@Suppress("LocalVariableName", "PropertyName")
open class AlgorithmFirst(
    private val criteria: List<List<Criteria>>,
    private val weights: List<Double>,
    val T: List<Double>
) {

    /** @return the reduced value in the range [0;1] */
    protected fun List<Double>.normalizeDoubles(): List<Double> = this.sum().let { weightsSum ->
        this.map { it / weightsSum }
    }


    /** @return the reduced value of the criteria in the range [0;1] */
    protected fun List<Criteria>.normalizeCriteria(): List<Double> =
        this.sumOf { it.digitalValue.toDouble() }.let { sum ->
            this.map {
                (it.digitalValue.toDouble() / sum).let { value ->
                    if (it is Criteria.IsDescendingOrderForCalc) 1 - value else value
                }
            }
        }

    val normalizedWeights by lazy {
        weights.normalizeDoubles()
    }

    val normalizedCriteria by lazy {
        criteria.map { it.normalizeCriteria() }
    }

    private val normalizedT by lazy {
        T.normalizeDoubles()
    }


    private fun calculateMatrixZ(
        t: List<Double>,
        normalizedO: List<List<Double>>
    ): List<List<Double>> {
        return t.mapIndexed { i, t_i ->
            normalizedO[i].map { o_i_j ->
                val minJ_Oij = normalizedO[i].min()
                val maxJ_Oij = normalizedO[i].max()
                1.0 - abs(t_i - o_i_j) / max(t_i - minJ_Oij, maxJ_Oij - t_i)
            }
        }
    }

    val matrixZ by lazy {
        calculateMatrixZ(normalizedT, criteria.map { it.map { i -> i.digitalValue.toDouble() } })
    }
}


sealed class Convolution {

     fun <T> List<List<T>>.transpose(): List<List<T>> =
        List(this[0].size) { i -> this.map { it[i] } }

    fun calculateCriteria(
        normalizedWeights: List<Double>,
        normalizedO: List<List<Double>>
    ): List<Double> = normalizedO.transpose().map {
        this.invoke(normalizedWeights, it)
    }

    /** @return the best alternative and its index */
    fun getBestAlternative(calculatedCriteria: List<Double>) =
            calculatedCriteria.indexOf(calculatedCriteria.max())


    protected abstract fun calculate(
        weightsNormalized: List<Double>,
        normalizedO: List<Double>
    ): Double

    operator fun invoke(
        weightsNormalized: List<Double>,
        normalizedO: List<Double>
    ): Double = require(normalizedO.size == weightsNormalized.size) {
        "Кількість вагових коефіцієнтів не співпадає з кількістю нормованих ваг"
    }.let { calculate(weightsNormalized, normalizedO) }


    data object PessimisticConvolution : Convolution() {
        override fun calculate(
            weightsNormalized: List<Double>,
            normalizedO: List<Double>
        ) = 1.0 / weightsNormalized.reduceIndexed { index, acc, item ->
            acc + item / normalizedO[index]
        }
    }

    data object CautiousConvolution : Convolution() {
        override fun calculate(
            weightsNormalized: List<Double>,
            normalizedO: List<Double>
        ) = weightsNormalized.foldIndexed(1.0) { index, acc, item ->
            acc * normalizedO[index].pow(item)
        }
    }

    data object AverageConvolution : Convolution() {
        override fun calculate(
            weightsNormalized: List<Double>,
            normalizedO: List<Double>
        ) = weightsNormalized.foldIndexed(0.0) { index, acc, item ->
            acc + item * normalizedO[index]
        }
    }

    data object OptimisticConvolution : Convolution() {
        override fun calculate(
            weightsNormalized: List<Double>,
            normalizedO: List<Double>
        ) = sqrt(
            weightsNormalized.reduceIndexed { index, acc, item ->
                acc + item * normalizedO[index].pow(2)
            }
        )
    }
}
