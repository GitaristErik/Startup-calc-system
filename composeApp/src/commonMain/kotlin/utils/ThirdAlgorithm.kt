package utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingFlat
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.Color
import third.StringsData
import utils.SecondAlgorithm.*

class ThirdAlgorithm {

    enum class L(
        override val title: String, override val description: String = "",
    ) : BaseLEnum, IconLEnum {

        LOW(
            "Н", "Низький рівень ризику",
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingUp
        },

        LOW_MEDIUM(
            "НС", "Рівень ризику нижче середнього"
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingUp
        },

        MEDIUM(
            "С", "Середній рівень ризику"
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingFlat
        },

        HIGH(
            "В", "Високий рівень ризику",
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingDown
        },

    }

    enum class Y_LINGUISTIC(val title: String, val color: Color, val textColor: Color) {
        HIGH(
            "високий",
            Color(0xffadf7a4),
            Color(0xff00ad0e)
        ),

        MEDIUM_HIGH(
            "вище середнього",
            Color(0xffadf7a4),
            Color(0xff00ad0e)
        ),

        MEDIUM(
            "середній",
            Color(0xfff8deb5),
            Color(0xffde7a1d)
        ),

        LOW(
            "низький",
            Color(0xffffcccf),
            Color(0xffca1e17)
        ),

        VERY_LOW(
            "дуже низький",
            Color(0xffffcccf),
            Color(0xffca1e17)
        );

        fun getFullTitle() = "${prefix}$title"

        companion object {
            const val delimiter = " – "
            const val prefix = "рейтинг команди розробників стартап проекту"
        }
    }

    private fun getRateTeams(teamScore: Double): String = when (teamScore) {
        in 0.0..0.21 -> "Рівень безпеки фінансування проєкту низький"
        in 0.21..0.36 -> "Рівень безпеки фінансування проєкту нижче середнього"
        in 0.36..0.67 -> "Рівень безпеки фінансування проєкту середній"
        in 0.67..0.87 -> "Рівень безпеки фінансування проєкту вище середнього"
        in 0.87..1.0 -> "Рівень безпеки фінансування проєкту високий"
        else -> "Невідомий рівень безпеки фінансування проєкту"
    }

    //Linguistic interpretation
    val teamRate: Y_LINGUISTIC by lazy {
        Y_LINGUISTIC.VERY_LOW
    }

    companion object {
        @JvmStatic
        private fun calculateIndexedKeysK() = StringsData.defaultData
            .flatMap { (key, list) ->
                list.mapIndexedNotNull { index, it ->
                    if (it.second == null || it.first == null) {
                        return@mapIndexedNotNull null
                    }

                    val newKey = when (key) {
                        "leaders", "teams" -> "2"
                        else -> key
                    }
                    val newIndex = when (key) {
                        "teams" -> index + 3
                        else -> index
                    }

                    "$newKey|$newIndex"
                }
            }

        @JvmStatic
        val indexedKeysK by lazy { calculateIndexedKeysK() }
    }

}