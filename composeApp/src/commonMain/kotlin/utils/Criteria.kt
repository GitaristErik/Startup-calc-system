package utils

sealed interface Criteria {
    val digitalValue: Number

    val localizedName: String

    /** Using for calculate the best alternative.
     * For example: The best variant is alternative with low price, no high
     * */
    interface IsDescendingOrderForCalc

    enum class NFC(
        override val digitalValue: Int,
        override val title: String
    ) : Criteria, DropdownEnum {
        Yes(1, "Так"),
        No(0, "Ні");

        override val localizedName = "NFC"

        companion object {
            val defaultDataSet
                get() = listOf(Yes, Yes, Yes, No) to Yes
        }
    }


    enum class Processor(
        override val title: String,
        override val digitalValue: Int
    ) : Criteria, DropdownEnum {
        Snapdragon8Gen1("Snapdragon 8 Gen 1", 1175722),
        MediaTekDimensity9000("Mediatek Dimensity 9000", 1043983),
        Exynos1280("Exynos 1280", 466140),
        Snapdragon7Gen1("Snapdragon 7 Gen 1", 661703),
        AppleA16Bionic("Apple A16 Bionic", 1433108);

        override val localizedName = "Процесор"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Snapdragon8Gen1,
                    MediaTekDimensity9000,
                    Exynos1280,
                    AppleA16Bionic
                ) to Snapdragon8Gen1
        }
    }

    enum class OS(
        override val title: String,
        override val digitalValue: Int
    ) : Criteria, DropdownEnum {
        Android13("Android 13", 13),
        Android12("Android 12", 12),
        Android11("Android 11", 11),
        IOS15("IOS 15", 12),
        IOS16("IOS 16", 13);

        override val localizedName = "Операційна система"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Android13,
                    Android11,
                    Android12,
                    IOS16
                ) to Android13
        }
    }

    data class Price(override val digitalValue: Double) : Criteria, IsDescendingOrderForCalc {
        override val localizedName: String = "Ціна"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Price(15000.0),
                    Price(22000.0),
                    Price(30000.0),
                    Price(37000.0)
                ) to Price(10000.0)
        }
    }

    data class Rating(override val digitalValue: Double) : Criteria {
        override val localizedName: String = "Рейтинг"
        companion object {
            val defaultDataSet
                get() = listOf(
                    Rating(4.5),
                    Rating(4.2),
                    Rating(4.4),
                    Rating(4.8)
                ) to Rating(5.0)
        }
    }

    data class DisplayDiagonal(override val digitalValue: Double) : Criteria {
        override val localizedName: String = "Діагональ екрану"

        companion object {
            val defaultDataSet
                get() = listOf(
                    DisplayDiagonal(6.7),
                    DisplayDiagonal(6.3),
                    DisplayDiagonal(6.5),
                    DisplayDiagonal(6.1)
                ) to DisplayDiagonal(6.6)
        }
    }

    data class AccumulatorCapacity(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Об'єм аккумулятора"

        companion object {
            val defaultDataSet
                get() = listOf(
                    AccumulatorCapacity(5000),
                    AccumulatorCapacity(4200),
                    AccumulatorCapacity(3500),
                    AccumulatorCapacity(3000)
                ) to AccumulatorCapacity(5300)
        }
    }

    data class Weight(override val digitalValue: Double) : Criteria {
        override val localizedName: String = "Вага"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Weight(200.0),
                    Weight(180.0),
                    Weight(160.0),
                    Weight(190.0)
                ) to Weight(167.0)
        }
    }

    data class Ram(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Оперативна пам'ять"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Ram(8),
                    Ram(6),
                    Ram(8),
                    Ram(4),
                ) to Ram(8)
        }
    }

    data class CamerasCount(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Кількість камер"

        companion object {
            val defaultDataSet
                get() = listOf(
                    CamerasCount(3),
                    CamerasCount(2),
                    CamerasCount(4),
                    CamerasCount(1),
                ) to CamerasCount(3)
        }
    }

    data class Storage(override val digitalValue: Int) : Criteria {
        override val localizedName: String = "Об'єм пам'яті"

        companion object {
            val defaultDataSet
                get() = listOf(
                    Storage(256),
                    Storage(128),
                    Storage(512),
                    Storage(128),
                ) to Storage(256)
        }
    }
}