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
fun TableFirstLevel(
    calculator: FirstAlgorithm,
    titleTypography: TextStyle = MaterialTheme.typography.titleMedium,
    bodyTypography: TextStyle = MaterialTheme.typography.bodyMedium,
) {

    Text(
        text = StringsData.labelTable1,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
    )

    val columnWeight = listOf(.15f, .2f, .2f, .2f, .2f)
    val paddingVertical = 8.dp

    Column(
        Modifier.padding(horizontal = 12.dp)
    ) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = paddingVertical),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = StringsData.labelTableFinal,
                modifier = Modifier.weight(columnWeight[0]),
                textAlign = TextAlign.Left,
                style = titleTypography
            )
            Text(
                text = StringsData.labelTableFinal,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(columnWeight[1]),
                style = titleTypography,
            )
            Text(
                text = StringsData.labelTableFinal,
                modifier = Modifier.weight(columnWeight[2]),
                textAlign = TextAlign.Center,
                style = titleTypography,
            )
            Text(
                modifier = Modifier.weight(columnWeight[3]),
                text = StringsData.labelTableFinal,
                textAlign = TextAlign.Center,
                style = titleTypography,
            )
            Text(
                modifier = Modifier.weight(columnWeight[4]),
                text = StringsData.labelTableFinal,
                style = titleTypography,
                textAlign = TextAlign.Right,
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier.height(1.dp).fillMaxWidth()
        )

        List(5) { "G${it + 1}" }.forEachIndexed { index, title ->
            Row(
                Modifier.fillMaxWidth().padding(vertical = paddingVertical),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    style = bodyTypography,
                    text = title,
                    modifier = Modifier.weight(columnWeight[0]),
                    textAlign = TextAlign.Left
                )
                Text(
                    text = calculator.G[index].toString(),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(columnWeight[1]),
                    style = bodyTypography,
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = "%.2f".format(calculator.membershipAlpha[index]),
                    textAlign = TextAlign.Center,
                    style = bodyTypography,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(columnWeight[2]),
                )
                Text(
                    style = bodyTypography,
                    textAlign = TextAlign.Center,
                    text = calculator.T[index].toString(),
                    modifier = Modifier.weight(columnWeight[3]),
                    color = MaterialTheme.colorScheme.secondary,
                )
                Text(
                    text = "%.2f".format(calculator.membershipAlphaDesired[index]),
                    style = bodyTypography,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(columnWeight[4]),
//                    textAlign = TextAlign.Right
                )
            }
            Divider(
                color = Color.LightGray, modifier = Modifier.height(1.dp)
//                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}
