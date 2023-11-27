package utils

import kotlin.math.max
import kotlin.math.pow

class FirstAlgorithm(
    val G: List<Int>,
    val A: List<Int>,
    val B: List<Int>,
    val T: List<Int>,
    val alphaU: List<Int>,
    val P: List<Int>,
) {

    /**
     * S-подібна функція належності
     * @param gi згортка суми балів по градаційній шкалі для розглядуваного стартапу
     * @param a згортка суми мінімальних балів градаційної шкали оцінювання за критеріями у групі
     * @param b згортка суми максимальних балів градаційної шкали оцінювання за критеріями у групі
     * @return значення функції належності
     */
    private fun sMembership(gi: Double, a: Double, b: Double) = when {
        gi <= a -> 0.0

        a < gi && gi <= ((a + b) / 2) -> {
            2 * ((gi - a) / (b - a)).pow(2)
        }

        (a + b) / 2 < gi && gi < b -> {
            1 - 2 * ((b - gi) / (b - a)).pow(2)
        }

        gi >= b -> 1.0

        else -> Double.NaN
    }

    private val calculateMembershipS = { index: Int, item: Int ->
        sMembership(item.toDouble(), A[index].toDouble(), B[index].toDouble())
    }

    val membershipAlpha by lazy { G.mapIndexed(calculateMembershipS) }
    val membershipAlphaDesired by lazy { T.mapIndexed(calculateMembershipS) }


    // ------------------------------

    private interface MembershipFunctionU {

        operator fun invoke(x: Double, α: Double): Double = Double.NaN

        val numberU: Int
    }

    private val membershipFunctionU1 = object : MembershipFunctionU {
        override operator fun invoke(x: Double, α: Double) = when {
            x <= (α - (α / 2)) -> 1.0

            α - (α / 2) < x && x <= α - (α / 4) -> {
                (3 * α - 4 * x) / α
            }

            else -> Double.NaN
        }

        override val numberU: Int
            get() = 1
    }

    private val membershipFunctionU2 = object : MembershipFunctionU {
        override operator fun invoke(x: Double, α: Double) = when {
            α - (α / 2) < x && x <= α - (α / 4) -> {
                (4 * x - 2 * α) / α
            }

            α - (α / 4) < x && x <= α -> {
                (4 * α - 4 * x) / α
            }

            else -> Double.NaN
        }

        override val numberU: Int
            get() = 2
    }

    private val membershipFunctionU3 = object : MembershipFunctionU {
        override operator fun invoke(x: Double, α: Double) = when {
            α - (α / 4) < x && x <= α -> {
                (4 * x - 3 * α) / α
            }

            α < x && x <= α + (α / 4) -> {
                (5 * α - 4 * x) / α
            }

            else -> Double.NaN
        }

        override val numberU: Int
            get() = 3
    }

    private val membershipFunctionU4 = object : MembershipFunctionU {
        override operator fun invoke(x: Double, α: Double) = when {
            α < x && x <= α + (α / 4) -> {
                (4 * x - 4 * α) / α
            }

            α + (α / 4) < x && x <= α + (α / 2) -> {
                (6 * α - 4 * x) / α
            }

            else -> Double.NaN
        }

        override val numberU: Int
            get() = 4
    }

    private val membershipFunctionU5 = object : MembershipFunctionU {
        override operator fun invoke(x: Double, α: Double) = when {
            α + (α / 4) < x && x <= α + (α / 2) -> {
                (4 * x - 5 * α) / α
            }

            x >= α + (α / 2) -> 1.0

            else -> Double.NaN
        }

        override val numberU: Int
            get() = 5
    }

    // ------------------------------

    private fun calculateU(
        membershipAlpha: List<Double>,
        membershipAlphaDesired: List<Double>
    ): List<Map<Int, Double>> = with(membershipAlpha.zip(membershipAlphaDesired)) {
        val functionsU = listOf(
            membershipFunctionU1,
            membershipFunctionU2,
            membershipFunctionU3,
            membershipFunctionU4,
            membershipFunctionU5,
        )

        map { (alpha, alphaDesired) ->
            functionsU.mapNotNull { it -> // membershipFunctionU[n]
                it(alpha, alphaDesired).let { res ->
                    if (res.isNaN()) null
                    else it.numberU to res
                }
            }.toMap()
        }
    }

    val calculatedU by lazy {
        calculateU(membershipAlpha, membershipAlphaDesired)
    }

    private fun calculateMaxScore(u: Map<Int, Double>, indexAlphaU: Int): Double {
        val a: Double = if (u.containsKey(indexAlphaU)) u[indexAlphaU]!! else 0.0
        val b: Double = when {
            u.containsKey(indexAlphaU + 1) -> u[indexAlphaU + 1]!! / 2.0
            u.containsKey(indexAlphaU - 1) -> u[indexAlphaU - 1]!! / 2.0
            else -> 0.0
        }

        return max(a, b)
    }

    val scoresByCriterias by lazy {
        calculatedU.mapIndexed { index, it ->
            calculateMaxScore(it, alphaU[index])
        }
    }

    // ------------------------------

    val normalizedP by lazy {
        P.map { it.toDouble() / P.sum() }
    }

    val aggregatedScore by lazy {
        normalizedP.foldIndexed(0.0) { index, start, item ->
            start + item * scoresByCriterias[index]
        }
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun ratingScale(m: Double) = when (m) {
        in 0.67..1.0 -> M.VERY_HIGH
        in 0.47..<0.67 -> M.HIGH
        in 0.36..<0.47 -> M.MEDIUM
        in 0.21..<0.36 -> M.LOW
        in 0.0..<0.21 -> M.VERY_LOW
        else -> M.MEDIUM
    }

    val ratedScale by lazy { ratingScale(aggregatedScore) }


    enum class M(val title: String) {
        VERY_LOW("Оцінка ідеї дуже низька"),
        LOW("Оцінка ідеї низька"),
        MEDIUM("Оцінка ідеї середня"),
        HIGH("Оцінка ідеї вище середнього"),
        VERY_HIGH("Оцінка ідеї висока")
    }

}
