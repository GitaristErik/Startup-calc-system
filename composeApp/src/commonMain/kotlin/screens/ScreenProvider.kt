package screens

import androidx.compose.runtime.Composable

interface ScreenProvider {

    @Composable
    fun firstScreen()

    @Composable
    fun secondScreen()

    @Composable
    fun thirdScreen()
}