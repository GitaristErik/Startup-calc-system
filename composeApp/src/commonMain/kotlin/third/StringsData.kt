package third

import utils.ThirdAlgorithm
import utils.ThirdAlgorithm.L

object StringsData {

    val labelsK = mapOf(
        "1" to "Стабільність та згуртованість команди",
        "2" to "Професійні компетенції та досвід команди",
        "leaders" to "Професійні компетенції лідерів",
        "teams" to "Професійні компетенції команди",
        "3" to "Професійна активність команди",
    )

    val defaultData: Map<String, List<Triple<L?, Double?, Int>>> by lazy {
        mapOf(
            "1" to listOf(
                // Стабільність та згуртованість команди
                Triple(null, null, 10),
                Triple(L.HIGH, 0.8, 8),
                Triple(L.MEDIUM, 0.9, 9),
            ),
            "2" to listOf(
                //  Професійні компетенції та досвід команди
                Triple(null, null, 9),
            ),
            "leaders" to listOf(
                // Професійні компетенції лідерів
                Triple(null, null, 10),
                Triple(L.HIGH, 0.7, 8),
                Triple(L.MEDIUM, 0.8, 10),
                Triple(L.LOW_MEDIUM, 0.6, 9),
            ),
            "teams" to listOf(
                // Професійні компетенції команди
                Triple(null, null, 8),
                Triple(L.MEDIUM, 0.5, 10),
                Triple(L.MEDIUM, 0.7, 7),
            ),
            "3" to listOf(
                // Професійна активність команди
                Triple(null, null, 8),
                Triple(L.LOW_MEDIUM, 0.8, 8),
                Triple(L.HIGH, 0.9, 6),
                Triple(L.HIGH, 0.9, 7),
                Triple(L.MEDIUM, 0.8, 9),
            )
        )
        /* mapOf(
            "1" to listOf(
//                CriteriaMain("Стабільність та згуртованість команди", 10),
                CriteriaMain(10),
                CriteriaK(L.HIGH, 0.8, 8),
                CriteriaK(L.MEDIUM, 0.9, 9),
            ),
            "2" to listOf(
//                CriteriaMain("Професійні компетенції та досвід команди", 9),
                CriteriaMain(9),
            ),
            "leaders" to listOf(
//                CriteriaMain("Професійні компетенції лідерів", 10),
                CriteriaMain(10),
                CriteriaK(L.HIGH, 0.7, 8),
                CriteriaK(L.MEDIUM, 0.8, 10),
                CriteriaK(L.LOW_MEDIUM, 0.6, 9),
            ),
            "teams" to listOf(
//                CriteriaMain("Професійні компетенції команди", 8),
                CriteriaMain(8),
                CriteriaK(L.MEDIUM, 0.5, 10),
                CriteriaK(L.MEDIUM, 0.7, 7),
            ),
            "3" to listOf(
//                CriteriaMain("Професійна активність команди", 8),
                CriteriaMain(8),
                CriteriaK(L.LOW_MEDIUM, 0.8, 8),
                CriteriaK(L.HIGH, 0.9, 6),
                CriteriaK(L.HIGH, 0.9, 7),
                CriteriaK(L.MEDIUM, 0.8, 9),
            )
        )*/
    }

    val labelRiskLevel = "Рівень показника"

    val labelCalculate = "Розрахувати"

    val labelTable1 = "Фазифікація вхідних сигналів"
    val labelTable1Col1 = "Назва критеріїв"
    val labelTable1Col2 = "x"

    val labelCalculatedNeuro = "Обчислений вихід нейрона за функцією активації"

    val labelResult = "Дефазифікація даних та визначення рейтингу стартап команд"
}