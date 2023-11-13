package org.example.project

import App
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.screens.FirstScreen
import org.example.project.screens.ThirdScreen
import screens.ScreenProvider
import screens.SecondScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(ScreenProviderImpl)
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App(ScreenProviderImpl)
}

object ScreenProviderImpl : ScreenProvider {
    @Composable
    override fun firstScreen() = FirstScreen()

    @Composable
    override fun secondScreen() = SecondScreen()

    @Composable
    override fun thirdScreen() = ThirdScreen()

}
