package first

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import utils.FirstAlgorithm

@Composable
fun TableSecondLevel(
    calculator: FirstAlgorithm,
    titleTypography: TextStyle = MaterialTheme.typography.titleMedium,
    bodyTypography: TextStyle = MaterialTheme.typography.bodyMedium,
) {

    Text(
        text = StringsData.labelTable2,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
    )

    val columnWeight = listOf(.1f, .2f)
    val paddingVertical = 8.dp

    Column(
        Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = paddingVertical),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = StringsData.labelTableFinal,
                modifier = Modifier.weight(columnWeight[0]),
                textAlign = TextAlign.Center,
                style = titleTypography,
            )
            Text(
                textAlign = TextAlign.Center,
                text = StringsData.labelTableFinal,
                modifier = Modifier.weight(columnWeight[1]),
                style = titleTypography,
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier.height(1.dp).fillMaxWidth()
        )

        List(5) { "U${it + 1}" }.forEachIndexed { index, title ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = paddingVertical),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = bodyTypography,
                    modifier = Modifier.weight(columnWeight[0]),
//                    alignment = TextAlign.Left
                )
                Text(
                    text = calculator.calculatedU[index].map { (k, v) ->
                        "U$k=" + "%.2f".format(v)
                    }.joinToString(separator = StringsData.labelTableFinal),
                    modifier = Modifier.weight(columnWeight[1]),
                    color = MaterialTheme.colorScheme.primary,
                    textAlign = TextAlign.Center,
                    style = bodyTypography,
                )
            }

            Divider(
                color = Color.LightGray, modifier = Modifier.height(1.dp).fillMaxWidth()
            )
        }
    }
}
