package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun LabelWithIndexes(
    label: String,
    indexTop: String,
    indexBottom: String,
    modifier: Modifier = Modifier,
    suffix: String? = null,
    color: Color = MaterialTheme.colorScheme.tertiary,
    fontStyle: FontStyle = FontStyle.Italic,
    fontWeights: Pair<FontWeight, FontWeight> = FontWeight.Normal to FontWeight.Normal,
    styles: Pair<TextStyle, TextStyle> = MaterialTheme.typography.titleLarge to MaterialTheme.typography.labelSmall,
) {
    Row(modifier = modifier, verticalAlignment = Alignment.CenterVertically) {
        val (mainStyle, subStyle) = styles
        val (fontWeightMain, fontWeightSub) = fontWeights

        val makeText: @Composable (String, Boolean) -> Unit = { text, isTitle ->
            Text(
                text = text,
                style = if (isTitle) mainStyle else subStyle,
                color = color,
                fontStyle = fontStyle,
                fontWeight = if (isTitle) fontWeightMain else fontWeightSub
            )
        }
        makeText(label, true)
        Column(verticalArrangement = Arrangement.Center) {
            makeText(indexTop, false)
//                    Spacer(modifier = Modifier.height(4.dp))
            makeText(indexBottom, false)
        }
        suffix?.let {
            Text(
                it,
                style = mainStyle,
                color = color,
                fontWeight = fontWeightMain,
            )
        }
    }
}