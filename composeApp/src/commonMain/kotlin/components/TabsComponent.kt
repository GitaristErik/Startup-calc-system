package components

import NavItem
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable


@Composable
fun TabsComponent(currentTab: NavItem, onTabSelected: (NavItem) -> Unit) {
    val tabs = NavItem.entries.toTypedArray()
    TabRow(
        selectedTabIndex = tabs.indexOf(currentTab),
    ) {
        tabs.forEachIndexed { index, tab ->
            Tab(
                selected = currentTab == tab,
                onClick = { onTabSelected(tab) },
                text = { Text(tab.nameItem) },
            )
        }
    }
}
