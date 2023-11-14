package utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingFlat
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.vector.ImageVector

class SecondAlgorithm(

) {

    enum class L(
        val title: String, val description: String = "", val imageIcon: ImageVector? = null
    ) {

        LOW(
            "Н",
            "Низький рівень ризику",
            Icons.Outlined.TrendingUp
        ),

        LOW_MEDIUM(
            "НС",
            "Рівень ризику нижче середнього",
            Icons.Outlined.TrendingUp
        ),

        MEDIUM(
            "С",
            "Середній рівень ризику",
            Icons.Outlined.TrendingFlat
        ),

        HIGH_MEDIUM(
            "ВС",
            "Рівень ризику вище середнього",
            Icons.Outlined.TrendingDown
        ),

        HIGH(
            "В",
            "Високий рівень ризику",
            Icons.Outlined.TrendingDown
        ),
    }


    val resultingTermEstimate by lazy {
        listOf(L.LOW, L.LOW_MEDIUM, L.MEDIUM, L.HIGH_MEDIUM)
    }

    val aggregatedScore by lazy {
        listOf(0.65, 0.56, 0.45, 0.34)
    }

    // "Generalised project risk assessment for groups of criteria α"
    val generalisedRiskAssessment by lazy {
        listOf(0.65, 0.56, 0.45, 0.34)
    }

    val aggregatedScoreRisc by lazy {
        0.56
    }

    //Linguistic interpretation of the level of security of project financing
    val linguisticInterpretation by lazy {
        "Низький рівень ризику"
    }

}