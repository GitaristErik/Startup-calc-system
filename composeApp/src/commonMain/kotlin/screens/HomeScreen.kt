package screens

import NavItem
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.TabsComponent

@Composable
fun HomeScreen(screenProvider: ScreenProvider) {
    var navItemState by remember { mutableStateOf(NavItem.First) }

    Column(
        modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()).imePadding(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {

        TabsComponent(
            currentTab = navItemState,
            onTabSelected = { navItemState = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (navItemState) {
            NavItem.First -> screenProvider.firstScreen()
            NavItem.Second -> screenProvider.secondScreen()
            NavItem.Third -> screenProvider.thirdScreen.invoke(this)
        }
    }
}
