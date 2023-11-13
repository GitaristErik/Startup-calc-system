package first

object InputHelper {

    val labelG = "Групи критеріїв"
    val arrayLabelsG = listOf<String>(
        "Cуть ідеї",
        "Aвтори ідеї",
        "Порівняльна характеристика ідеї",
        "Комерційна значимість ідеї",
        "Очікувані результати"
    )
    val arrayG: List<Int> = listOf<Int>(70, 50, 40, 150, 65)


    val labelA = "Згортка суми найгірших відповідей"
    val arrayA: List<Int> = listOf(20, 15, 10, 50, 25)


    val labelB = "Згортка суми найкращих відповідей"
    val arrayB: List<Int> = listOf(115, 60, 50, 225, 90)


    val labelT = "«Бажанні значення інвестора»"
    val arrayT: List<Int> = listOf(80, 55, 35, 165, 50)


    val labelU = "«Бажанні значення» терму"
    val arrayU: List<Int> = listOf(3, 3, 5, 4, 3)


    val labelP = "Вагові коефіцієнти"
    val arrayP: List<Int> = listOf(10, 8, 6, 7, 4)


    val labelCalculate = "Розрахувати"


    val labelTable1 = "Отримані дані по стартапу згідно першого рівня"
    val labelTable1Col1 = "Групи критеріїв"
    val labelTable1Col2 = "Бальна оцінка"
    val labelTable1Col3 = "Функція належності бальної оцінки"
    val labelTable1Col4 = "Бажане значення"
    val labelTable1Col5 = "Функція належності бажаних значень"

    val labelTable2 = "Отримані дані по стартапу згідно другого рівня"
    val labelTable2Col1 = "Отриманий терм"
    val labelTable2Col2 = "Достовірність терму \n(значення функції належності)"
    val labelTable2Delimiter = " або "

    val labelTable3 = "Значення оцінок по групах критеріїв"

    val labelTable4 = "Нормовані вагові коефіцієнти"


    val aggregatedScore = "Агрегована оцінка"

    val result = "Результат"
}