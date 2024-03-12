package utils

import utils.Electre.ElectreDirection.*
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class Electre(
    private val criteria: List<List<Double>>,
    private val verbosePrintOutput: Boolean = false
) {

    enum class ElectreDirection {
        MAX, MIN
    }

    private fun printElectre(
        concordanceMatrix: List<DoubleArray>,
        nonDiscordanceMatrix: List<DoubleArray>,
        outrankingMatrix: List<Array<Boolean?>>,
        kernels: List<String>,
        robustnessAnalysisResults: List<String>,
        frequentKernels: List<String>
    ) {

        println("concordanceMatrix: ")
        concordanceMatrix.forEach { row ->
            println(row.joinToString("\t\t") { "%.3f".format(it) })
        }
        println()

        println("nonDiscordanceMatrix: ")
        nonDiscordanceMatrix.forEach { row ->
            println(row.joinToString("\t\t") { "%.3f".format(it) })
        }
        println()

        println("outrankingMatrix: ")
        outrankingMatrix.forEach { row ->
            println(row.joinToString("\t\t"))
        }
        println()


        println(kernels)

        println("Robustness Analysis:")
        robustnessAnalysisResults.forEach { println(it) }

        println()

        println("Most Frequent Kernels:")
        println(frequentKernels.joinToString(", "))
    }


    /**
     * Perform robustness analysis on the concordance and non-discordance matrices
     * @param concordanceMatrix the concordance matrix
     * @param nonDiscordanceMatrix the non-discordance matrix
     * @return the robustness analysis results
     */
    private fun robustnessAnalysis(
        concordanceMatrix: List<DoubleArray>,
        nonDiscordanceMatrix: List<DoubleArray>
    ): Pair<List<String>, List<String>> {

        val robustnessAnalysisKernels = mutableMapOf<Double, List<String>>()

        generateSequence(0.5) { it + 0.025 }
            .takeWhile { it <= 1.0 }
            .forEach { threshold ->
                val outrankingMatrix = getOutrankingMatrix(
                    concordanceMatrix, nonDiscordanceMatrix, threshold
                )

                robustnessAnalysisKernels[threshold] = getKernels(outrankingMatrix)
            }

        val analysisResults = mutableListOf<String>()
        val occurrences = mutableMapOf<String, Int>()

        for ((key, value) in robustnessAnalysisKernels) {
            value.forEach {
                occurrences[it] = occurrences.getOrDefault(it, 1) + 1
            }
            analysisResults.add("[%.3f] = %s".format(key, value.joinToString(", ")))
        }

        val orderedCandidates = occurrences.entries
            .sortedByDescending { it.value }
            .map { "${it.key} (${it.value})" }

        return analysisResults to orderedCandidates
    }


    /**
     * Get the concordance and non-discordance matrices
     * @param weights the weights for each criterion
     * @param prefs the preferences for each criterion
     * @param vetoes the vetoes for each criterion
     * @param preferenceThresholds the preference thresholds for each criterion (optional for ELECTRE I-s)
     * @return the concordance and non-discordance matrices
     */
    private fun getElectre1Matrices(
        weights: List<Double>,
        prefs: List<ElectreDirection>,
        vetoes: List<Double>,
        preferenceThresholds: List<Double>? = null
    ): Pair<List<DoubleArray>, List<DoubleArray>> {

        val concordanceMatrix = List(criteria.size) { DoubleArray(criteria.size) { 0.0 } }
        val nonDiscordanceMatrix = List(criteria.size) { DoubleArray(criteria.size) { 0.0 } }

        for (x in criteria.indices) {
            for (y in x until criteria.size) {
                if (x == y) {
                    concordanceMatrix[x][y] = Double.NaN
                    nonDiscordanceMatrix[x][y] = Double.NaN
                    continue
                }

                val (a, b) = criteria[x] to criteria[y]
                var (av, bv) = 0.0 to 0.0

                var (aRespectsVetoes, bRespectsVetoes) = true to true


                for (idx in weights.indices) {
                    val (w, p, v) = Triple(weights[idx], prefs[idx], vetoes[idx])

                    val bestVal = if (p == MAX) {
                        max(a[idx], b[idx])
//                        a[idx].coerceAtLeast(b[idx])
                    } else {
                        min(a[idx], b[idx])
//                        a[idx].coerceAtMost(b[idx])
                    }

                    val diff = abs(b[idx] - a[idx])

                    // NOTE: ELECTRE I-v
                    var points = if (diff != 0.0) 0.0 else w

                    // NOTE: ELECTRE I-s
                    if (preferenceThresholds != null) {
                        val prefThreshold = preferenceThresholds[idx]
                        if (diff < prefThreshold) {
                            points = (1 - (diff / prefThreshold)) * w
                        }
                    }

                    if (bestVal == a[idx]) {
                        av += w
                        bv += points
                        bRespectsVetoes = bRespectsVetoes && diff < v
                    } else {
                        av += points
                        bv += w
                        aRespectsVetoes = aRespectsVetoes && diff < v
                    }
                }

                concordanceMatrix[x][y] = av
                nonDiscordanceMatrix[x][y] = if (aRespectsVetoes) 1.0 else 0.0

                concordanceMatrix[y][x] = bv
                nonDiscordanceMatrix[y][x] = if (bRespectsVetoes) 1.0 else 0.0
            }
        }

        return concordanceMatrix to nonDiscordanceMatrix
    }

    /**
     * Get the outranking matrix
     * @param concordanceMatrix the concordance matrix
     * @param nonDiscordanceMatrix the non-discordance matrix
     * @param concordanceThreshold the concordance threshold
     * @return the outranking matrix
     */
    private fun getOutrankingMatrix(
        concordanceMatrix: List<DoubleArray>,
        nonDiscordanceMatrix: List<DoubleArray>,
        concordanceThreshold: Double
    ): List<Array<Boolean?>> {

        val size = criteria.size
        val outrankingMatrix = List(size) { Array<Boolean?>(size) { false } }

        for (x in criteria.indices) {
            for (y in criteria.indices) {
                if (x == y) {
                    outrankingMatrix[x][y] = null
                    continue
                }

                val ac = concordanceMatrix[x][y]
                val av = nonDiscordanceMatrix[x][y]
                val bc = concordanceMatrix[y][x]
                val bv = nonDiscordanceMatrix[y][x]

                outrankingMatrix[x][y] = ac > concordanceThreshold && av != 0.0 && !av.isNaN()
                outrankingMatrix[y][x] = bc > concordanceThreshold && bv != 0.0 && !bv.isNaN()
            }
        }

        return outrankingMatrix
    }

    /**
     * Get the kernels from the outranking matrix
     * @param resultMatrix the outranking matrix
     * @return the kernels
     * @see getOutrankingMatrix
     */
    private fun getKernels(resultMatrix: List<Array<Boolean?>>) =
        criteria.mapIndexedNotNull { col, _ ->
            var isKernel = true
            for (row in criteria.indices) {
                if (resultMatrix[row][col] != null && resultMatrix[row][col] == true) {
                    isKernel = false
                    break
                }
            }

            if (isKernel) (col + 1).toString()
            else null
        }

    /**
     * Solve the ELECTRE problem
     * @param weights the weights for each criterion
     * @param prefs the preferences for each criterion
     * @param vetoes the vetoes for each criterion
     * @param concordanceThreshold the concordance threshold
     * @param preferenceThresholds the preference thresholds for each criterion (optional for ELECTRE I-s)
     * @return the kernels and frequent kernels
     */
    fun solve(
        weights: List<Double>,
        prefs: List<ElectreDirection>,
        vetoes: List<Double>,
        concordanceThreshold: Double,
        preferenceThresholds: List<Double>? = null
    ): Pair<List<String>, List<String>> {

/*        // validate weights
        require(weights.size == criteria.size) {
            "Weights length must match criteria length"
        }

        // validate prefs
        require(prefs.size == criteria.size) {
            "Prefs length must match criteria length"
        }

        // validate vetoes
        require(vetoes.size == criteria.size) {
            "Vetoes length must match criteria length"
        }*/
/*

        // validate preference thresholds
        preferenceThresholds?.let {
            require(it.size == criteria.size) {
                "Preference thresholds length must match criteria length"
            }
        }
*/

        // calculate matrices
        val (concordanceMatrix, nonDiscordanceMatrix) =
            getElectre1Matrices(weights, prefs, vetoes, preferenceThresholds)

        // calculate kernels
        val outrankingMatrix = getOutrankingMatrix(
            concordanceMatrix, nonDiscordanceMatrix,
            concordanceThreshold
        )
        val kernels = getKernels(outrankingMatrix)

        // robustness analysis
        val (robustnessAnalysisResults, frequentKernels) =
            robustnessAnalysis(concordanceMatrix, nonDiscordanceMatrix)

        // print output
        if (verbosePrintOutput) {
            printElectre(
                concordanceMatrix,
                nonDiscordanceMatrix,
                outrankingMatrix,
                kernels,
                robustnessAnalysisResults,
                frequentKernels
            )
        }

        // return results
        return kernels to frequentKernels
    }
}

/*
val data = listOf(
    listOf(0.8557692307692308, 0.7884615384615384, 0.7115384615384616, 0.6442307692307692),
    listOf(0.2653061224489796, 0.22448979591836735, 0.24489795918367346, 0.2653061224489796),
    listOf(0.2854419557591456, 0.25345834244770454, 0.11316953604471816, 0.3479301657484317),
    listOf(0.3076923076923077, 0.23076923076923078, 0.3076923076923077, 0.15384615384615385),
    listOf(0.25, 0.125, 0.5, 0.125),
    listOf(0.3333333333333333, 0.3333333333333333, 0.3333333333333333, 0.0),
    listOf(0.3184713375796178, 0.267515923566879, 0.2229299363057325, 0.1910828025477707),
    listOf(0.3, 0.2, 0.4, 0.1),
    listOf(0.26171875, 0.24609374999999997, 0.25390625, 0.23828124999999997),
    listOf(0.25139664804469275, 0.23463687150837992, 0.24581005586592183, 0.2681564245810056),
    listOf(0.726027397260274, 0.7534246575342466, 0.7808219178082192, 0.7397260273972603),
)

val weights = listOf(0.08, 0.06666666666666667, 0.12, 0.06666666666666667, 0.10666666666666667, 0.04, 0.09333333333333334, 0.08, 0.12, 0.10666666666666667, 0.12)
val vetoes = listOf(0.06666666666666665, 0.10666666666666666, 0.12, 0.06, 0.07999999999999999, 0.13199999999999998, 0.07466666666666666, 0.09333333333333331, 0.10666666666666666, 0.12, 0.039999999999999994)
 */