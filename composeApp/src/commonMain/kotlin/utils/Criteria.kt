package utils

sealed interface Criteria {
    val digitalValue: Int

    val localizedName: String

    /** Using for calculate the best alternative.
     * For example: The best variant is alternative with low price, no high
     * */
    interface IsDescendingOrderForCalc

    enum class NFC(
        override val digitalValue: Int,
        override val localizedName: String
    ) : Criteria {
        Yes(1, "Так"),
        No(0, "Ні")
    }


    enum class Processor(
        override val localizedName: String,
        override val digitalValue: Int
    ) : Criteria {
        Snapdragon8Gen1("Snapdragon 8 Gen 1", 1175722),
        MediaTekDimensity9000("Mediatek Dimensity 9000", 1043983),
        Exynos1280("Exynos 1280", 466140),
        Snapdragon7Gen1("Snapdragon 7 Gen 1", 661703),
        AppleA16Bionic("Apple A16 Bionic", 1433108)
    }

    enum class OS(
        override val localizedName: String,
        override val digitalValue: Int
    ) : Criteria {
        Android13("Android 13", 13),
        Android12("Android 12", 12),
        Android11("Android 11", 11),
        IOS15("IOS 15", 12),
        IOS16("IOS 16", 13)
    }

    data class Price(override val digitalValue: Int) : Criteria, IsDescendingOrderForCalc {
        override val localizedName: String = "Ціна"
    }

    data class Rating(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Рейтинг"
    }

    data class DisplayDiagonal(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Діагональ екрану"
    }

    data class AccumulatorCapacity(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Об'єм аккумулятора"
    }

    data class Weight(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Вага"
    }

    data class Ram(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Оперативна пам'ять"
    }

    data class CamerasCount(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Кількість камер"
    }

    data class Storage(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Об'єм пам'яті"
    }
}