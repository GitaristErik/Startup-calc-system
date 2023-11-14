package utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingFlat
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.math.sqrt

class SecondAlgorithm(
    val K: List<List<Pair<L, Double>>>,
) {

    enum class L(
        val title: String, val description: String = "", val imageIcon: ImageVector? = null
    ) {

        LOW(
            "Н", "Низький рівень ризику", Icons.Outlined.TrendingUp
        ),

        LOW_MEDIUM(
            "НС", "Рівень ризику нижче середнього", Icons.Outlined.TrendingUp
        ),

        MEDIUM(
            "С", "Середній рівень ризику", Icons.Outlined.TrendingFlat
        ),

        HIGH_MEDIUM(
            "ВС", "Рівень ризику вище середнього", Icons.Outlined.TrendingDown
        ),

        HIGH(
            "В", "Високий рівень ризику", Icons.Outlined.TrendingDown
        ),
    }


    /**
     * Calculating the resulting term estimate of the group of criteria
     * @param kGroup [List] group of criteria
     * @return [Pair] term estimate
     */
    private fun calculateTermEstimate(
        kGroup: List<Pair<L, Double>>
    ): L = L.entries.firstOrNull { l ->
        kGroup.count { it.first == l } / kGroup.size.toDouble() >= 0.6
    } ?: L.MEDIUM

    val resultingTermEstimate by lazy {
        K.map { calculateTermEstimate(it) }
    }


    /**
     * Calculating the aggregated score
     * @param kGroup [List] group of criteria
     * @param termEstimate [L] term estimate
     * @return [Double] aggregated score
     */
    private fun calculateAggregatedScore(
        kGroup: List<Pair<L, Double>>,
        termEstimate: L
    ) = kGroup.mapNotNull { (k, risc) ->
        if (k == termEstimate) risc else null
    }.let { filteredList ->
        1f / filteredList.size * filteredList.sum()
    }


    /**
     * Calculating the aggregated score for all groups of criteria
     * @return [List] aggregated score
     */
    val aggregatedScore: List<Double> by lazy {
        resultingTermEstimate.mapIndexed { index, termEstimate ->
            calculateAggregatedScore(K[index], termEstimate)
        }
    }


    /** Estimate the term membership
     * @param aggRelAssess [Pair] aggregated relative assessment
     * @return [Pair] term membership and relative assessment
     */
    private fun estimateTermMembership(aggRelAssess: Pair<L, Double>): Pair<Double, Double> {
        val (lang, score) = aggRelAssess
        val a = RISK_PERCENTAGE_LEVELS[lang]!!.first
        val b = RISK_PERCENTAGE_LEVELS[lang]!!.second

        val x = if (score in 0.0..0.5) {
            sqrt(score / 2) * (b - a) + a
        } else if (0.5 < score && score <= 1f) {
            b - sqrt((1f - score) / 2f) * (b - a)
        } else {
            0.0
        }

        return x to x / 100f
    }

    /**
     * Calculating the relative assessment of the group of criteria
     * @param groupCriteria [List] group of criteria
     */
    val generalisedRiskAssessmentWithRelative by lazy {
        resultingTermEstimate.zip(aggregatedScore).map { estimateTermMembership(it) }
    }

    val generalisedRiskAssessment by lazy {
        generalisedRiskAssessmentWithRelative.map { it.second }
    }

    /**
     * Calculating the aggregate risk score for all groups of criteria
     * @return [Double] aggregated score
     */
    val aggregatedScoreRisc by lazy {
        (1.0 / 4.0) * generalisedRiskAssessment.fold(0.0) { acc, d ->
            acc + (1.0 - d)
        }
    }

    /**
     * Determine the security level
     * @param aggregatedScoreRisc [Double] aggregated membership
     * @return [String] security level
     */
    private fun getSecurityLevel(aggregatedScoreRisc: Double): String = when (aggregatedScoreRisc) {
        in 0.0..0.21 -> "Рівень безпеки фінансування проєкту низький"
        in 0.21..0.36 -> "Рівень безпеки фінансування проєкту нижче середнього"
        in 0.36..0.67 -> "Рівень безпеки фінансування проєкту середній"
        in 0.67..0.87 -> "Рівень безпеки фінансування проєкту вище середнього"
        in 0.87..1.0 -> "Рівень безпеки фінансування проєкту високий"
        else -> "Невідомий рівень безпеки фінансування проєкту"
    }

    //Linguistic interpretation of the level of security of project financing
    val linguisticInterpretation by lazy {
        getSecurityLevel(aggregatedScoreRisc)
    }

    private companion object {
        val RISK_PERCENTAGE_LEVELS = mapOf(
            L.LOW to (0.0 to 20.0),
            L.LOW_MEDIUM to (20.0 to 40.0),
            L.MEDIUM to (40.0 to 60.0),
            L.HIGH_MEDIUM to (60.0 to 80.0),
            L.HIGH to (80.0 to 100.0)
        )
    }

}