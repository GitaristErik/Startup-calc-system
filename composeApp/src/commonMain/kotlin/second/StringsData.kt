package second

import utils.SecondAlgorithm.L

object StringsData {

    val labelsK = listOf(
        "0" to "Операційні ризики",
        "1" to "Інвестиційні ризики",
        "F" to "Фінансові ризики",
        "S" to "Ризики інноваційної діяльності",
    )

    val K = listOf(
        // «операційні ризики»
        listOf(
            L.LOW to 0.8,
            L.LOW_MEDIUM to 0.7,
            L.LOW_MEDIUM to 0.9,
            L.LOW to 0.6,
            L.LOW_MEDIUM to 0.7,
            L.MEDIUM to 0.5,
            L.LOW to 0.7,
            L.LOW to 0.8,
            L.LOW to 0.9,
        ),
        // «інвестиційні ризики»
        listOf(
            L.LOW to 0.7,
            L.LOW to 0.5,
            L.MEDIUM to 0.6,
            L.LOW to 0.8,
            L.HIGH_MEDIUM to 0.9,
        ),
        // «фінансові ризики»
        listOf(
            L.MEDIUM to 0.3,
            L.LOW_MEDIUM to 0.6,
            L.LOW_MEDIUM to 0.2,
            L.HIGH_MEDIUM to 0.5,
            L.LOW_MEDIUM to 0.6,
        ),
        // «ризики інноваційної діяльності»
        listOf(
            L.LOW_MEDIUM to 0.8,
            L.HIGH to 0.4,
            L.MEDIUM to 0.1,
            L.LOW_MEDIUM to 0.3,
            L.LOW_MEDIUM to 0.5,
        ),
    )


    val labelRiskLevel = "Рівень ризику"


    val labelCalculate = "Розрахувати"

    val labelTable1 = "Визначення результуючої терм-оцінки."
    val labelTable1Col1 = "Групи критеріїв"
    val labelTable1Col2 = "Результуюча\nтерм-оцінка"
    val labelTable1Col3 = "Агрегована оцінка"

    val labelTable2 = "Узагальнена оцінка ризику проекту для груп критеріїв α"

    val labelAggregatedScore = "Агрегована оцінка ризику"

    val result = "Лінгвістичне трактування рівня безпеки фінансування проекту"
}