package utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.TrendingDown
import androidx.compose.material.icons.outlined.TrendingFlat
import androidx.compose.material.icons.outlined.TrendingUp
import androidx.compose.ui.graphics.Color
import third.StringsData
import utils.SecondAlgorithm.*
import utils.ThirdAlgorithm.Y_LINGUISTIC.HIGH
import utils.ThirdAlgorithm.Y_LINGUISTIC.LOW
import utils.ThirdAlgorithm.Y_LINGUISTIC.MEDIUM
import utils.ThirdAlgorithm.Y_LINGUISTIC.MEDIUM_HIGH
import utils.ThirdAlgorithm.Y_LINGUISTIC.VERY_LOW
import kotlin.math.pow

class ThirdAlgorithm(
    val inputData: Map<String, List<Triple<L?, Double?, Int>>>
) {

    enum class L(
        override val title: String,
        override val description: String = "",
        val termBounds: Pair<Double, Double>,
    ) : BaseLEnum, IconLEnum {

        LOW(
            "Н", "Низький рівень", 0.0 to 2.0
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingUp
        },

        LOW_MEDIUM(
            "НС", "Рівень нижче середнього", 2.0 to 5.0
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingUp
        },

        MEDIUM(
            "С", "Середній рівень", 5.0 to 8.0
        ) {
            override fun getImageIcon() = Icons.Outlined.TrendingFlat
        },

        HIGH(
            "В", "Високий рівень", 8.0 to 10.0
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


    private val kWithoutLabels by lazy {
        inputData.mapValues { (_, v) ->
            v.mapNotNull {
                if (it.first != null && it.second != null) Triple(
                    it.first!!,
                    it.second!!,
                    it.third
                ) else null
            }
        }
    }

    private fun characteristicFunction(l: L, d: Double) = l.termBounds.second * d

    private val chars by lazy {
        kWithoutLabels.mapValues { (key, value) ->
            value.map {
                characteristicFunction(it.first, it.second)
            }
        }
    }

    private fun membership(char: Double): Double =
        if (char <= 0) {
            0.0
        } else if (1 < char && char <= 5) {
            0.02 * char.pow(2)
        } else if (5 < char && char < 10) {
            1 - 0.02 * (10 - char).pow(2)
        } else {
            1.0
        }

    private val calculatedMembership by lazy {
        chars.mapValues { it.value.map { i -> membership(i) } }
    }

    val calculatedMembershipFlatten by lazy {
        calculatedMembership.values.flatten()
    }


    private val weightsV by lazy {
        kWithoutLabels.mapValues { it.value.map { i -> i.third } }
    }

    private val weightsK by lazy {
        inputData.mapValues { it.value.first().third }
    }

    private fun groupPostsynapticPotential(): Triple<Double, Double, Double> {
        val z1 = (1.0 / weightsV["1"]!!.sum()) *
                (calculatedMembership["1"]!![0] * weightsV["1"]!![0] +
                        calculatedMembership["1"]!![1] * weightsV["1"]!![1])

        val weights2 = weightsV["leaders"]!! + weightsV["teams"]!!
        val calculatedMembership2 =
            calculatedMembership["leaders"]!! + calculatedMembership["teams"]!!

        val z21 = (1.0 / (weights2[0] + weights2[1])) * (calculatedMembership2[0] *
                weights2[0] + calculatedMembership2[1] * weights2[1])

        val z22 = (1.0 / (weights2[2] + weights2[3] + weights2[4])) *
                (calculatedMembership2[2] * weights2[2] +
                        calculatedMembership2[3] * weights2[3] +
                        calculatedMembership2[4] * weights2[4])

        val z2 = (1.0 / (weightsK["leaders"]!! + weightsK["teams"]!!)) *
                (z21 * weightsK["leaders"]!! + z22 * weightsK["teams"]!!)

        val z3 =
            (1.0 / weightsV["3"]!!.sum()) * (calculatedMembership["3"]!![0] * weightsV["3"]!![0] +
                    calculatedMembership["3"]!![1] * weightsV["3"]!![1] + calculatedMembership["3"]!![2] *
                    weightsV["3"]!![2] + calculatedMembership["3"]!![3] * weightsV["3"]!![3])

        return Triple(z1, z2, z3)
    }


    private val groupedPostsynapticPotential by lazy {
        groupPostsynapticPotential()
    }

    private fun calculatePostsynapticPotential(z: Triple<Double, Double, Double>): Triple<Double, Double, Double> {
        val kWeight = Triple(
            weightsK["1"]!!.toDouble(),
            weightsK["2"]!!.toDouble(),
            weightsK["3"]!!.toDouble()
        )
        val w1 = (kWeight.first / (kWeight.first + kWeight.second + kWeight.third)) * z.first
        val w2 = (kWeight.second / (kWeight.first + kWeight.second + kWeight.third)) * z.second
        val w3 = (kWeight.third / (kWeight.first + kWeight.second + kWeight.third)) * z.third

        return Triple(w1, w2, w3)
    }

    private val postsynapticPotential by lazy {
        calculatePostsynapticPotential(groupedPostsynapticPotential)
    }

    val defuzzification by lazy {
        val (w1, w2, w3) = postsynapticPotential
        w1 + w2 + w3
    }

    private fun getRateTeams(defuzzification: Double): Y_LINGUISTIC = when (defuzzification) {
        in 0.0..0.21 -> VERY_LOW
        in 0.22..0.37 -> LOW
        in 0.38..0.67 -> MEDIUM
        in 0.68..0.87 -> MEDIUM_HIGH
        in 0.87..1.0 -> HIGH
        else -> MEDIUM
    }

    val teamRate: Y_LINGUISTIC by lazy {
        getRateTeams(defuzzification)
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