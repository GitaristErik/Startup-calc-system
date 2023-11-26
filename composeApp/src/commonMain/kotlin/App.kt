import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import screens.FirstScreen
import screens.HomeScreen
import screens.ScreenProvider
import screens.SecondScreen
import screens.ThirdScreen

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App(
    screenProvider: ScreenProvider = object : ScreenProvider {
        @Composable
        override fun firstScreen() = FirstScreen()

        @Composable
        override fun secondScreen() = SecondScreen()

        override val thirdScreen: @Composable (ColumnScope.() -> Unit)
            get() = { ThirdScreen() }

    }
) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            HomeScreen(screenProvider)
        }
    }
}