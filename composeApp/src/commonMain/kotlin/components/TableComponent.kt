package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

data class TableColumn(
    val title: String,
    val weight: Float,
    val align: TextAlign = TextAlign.Center,
    val color: Color? = null,
    val fontStyle: FontStyle = FontStyle.Normal,
)

@Composable
fun TableComponent(
    tableColumns: List<TableColumn>,
    items: List<List<String>>,
    modifier: Modifier = Modifier,
    titleTypography: TextStyle = MaterialTheme.typography.titleMedium,
    bodyTypography: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    TableComponent(
        tableColumns = tableColumns,
        items = items,
        modifier = modifier,
        titleTypography = titleTypography,
        bodyContent = { index: Int, item: String ->
            Text(
                text = item,
                textAlign = tableColumns[index].align,
                style = bodyTypography,
                modifier = Modifier.weight(tableColumns[index].weight),
                color = tableColumns[index].color ?: Color.Unspecified,
                fontStyle = tableColumns[index].fontStyle,
            )
        }
    )
}

@Composable
fun TableComponent(
    tableColumns: List<TableColumn>?,
    items: List<List<String>>,
    bodyContent: @Composable (RowScope.(Int, String) -> Unit),
    modifier: Modifier = Modifier,
    titleTypography: TextStyle = MaterialTheme.typography.titleMedium,
) {

    Row(
        modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Absolute.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tableColumns?.forEach { (title, weight, align) ->
            Text(
                text = title,
                modifier = modifier.weight(weight),
                textAlign = align,
                style = titleTypography,
            )
        }
    }

    Divider(
        color = Color.LightGray, modifier = Modifier
            .height(1.dp)
            .fillMaxWidth()
    )

    items.forEachIndexed { index, list ->
        Row(
            modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            list.forEachIndexed { index, it ->
                bodyContent(index, it)
            }
        }

        if (index != items.lastIndex) {
            Divider(
                color = Color.LightGray, modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
            )
        }
    }
}
