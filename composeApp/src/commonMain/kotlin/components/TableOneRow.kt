package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TableOneRow(items: Map<String, String>, tableLabel: String) {

    Text(
        text = tableLabel,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
    )

    val paddingVertical = 8.dp

    Row(
        Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .height(IntrinsicSize.Min) //intrinsic measurements ,
    ) {
        items.forEach { (k, v) ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(paddingVertical),
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = k,
//                    modifier = Modifier.weight(columnWeight[0]),
                    style = MaterialTheme.typography.titleSmall
                )

                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
//                    .weight(1f)
                )

                Text(
                    text = v,
//                    modifier = Modifier.weight(columnWeight[1]),
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.W500,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            if (k != items.keys.last()) {
                Divider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
//                    .weight(1f)
                )
            }
        }
    }
}