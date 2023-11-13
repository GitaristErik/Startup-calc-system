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

        @Composable
        override fun thirdScreen() = ThirdScreen()

    }
) {
    MaterialTheme {/*
        var greetingText by remember { mutableStateOf("Hello World!") }
        var showImage by remember { mutableStateOf(false) }
        var navItemState by remember { mutableStateOf(NavItem.First) }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            *//*Button(onClick = {
                greetingText = "Compose: ${Greeting().greet()}"
                showImage = !showImage
            }) {
                Text(greetingText)
            }

            AnimatedVisibility(showImage) {
                Image(
                    painterResource("compose-multiplatform.xml"),
                    null
                )
            }

            TabsComponent(
                currentTab = navItemState,
                onTabSelected = { navItemState = it }
            )
            *//*

        }*/

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.surface
        ) {
            HomeScreen(screenProvider)
        }
    }
}