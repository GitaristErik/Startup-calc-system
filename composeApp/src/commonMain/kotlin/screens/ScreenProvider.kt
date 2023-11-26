package screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable

interface ScreenProvider {

    @Composable
    fun firstScreen()

    @Composable
    fun secondScreen()

    val thirdScreen: @Composable ColumnScope.() -> Unit
}