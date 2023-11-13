package components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp


@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float,
    modifier: Modifier = Modifier.padding(all = 4.dp),
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false,
    color: Color = MaterialTheme.colorScheme.onSurface,
) {
    Text(
        text = text,
        Modifier.weight(weight),
        style = if (title) MaterialTheme.typography.titleSmall else MaterialTheme.typography.bodyMedium,
//        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
        color = color,
    )
}