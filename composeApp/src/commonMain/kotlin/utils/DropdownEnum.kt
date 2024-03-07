package utils

import androidx.compose.ui.graphics.vector.ImageVector

interface DropdownEnum {

    val title: String

    val description: String?
        get() = null

    val separator: String
        get() = ": "

    fun getImageIcon(): ImageVector? = null
}