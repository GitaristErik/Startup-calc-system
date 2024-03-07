import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
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
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme(),
        typography = typography,
        shapes = shapes
    ) {
        Surface(
            modifier = Modifier.fillMaxSize()
                .systemBarsPadding(),
            color = MaterialTheme.colorScheme.surface
        ) {
            HomeScreen(screenProvider)
        }
    }
}