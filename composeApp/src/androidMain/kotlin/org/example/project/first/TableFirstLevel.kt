package org.example.project.first

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.TableCell
import first.InputHelper
import utils.FirstAlgorithm

@Composable
fun TableFirstLevel(calculator: FirstAlgorithm) {

    Text(
        text = InputHelper.labelTable1,
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
            Modifier
                .fillMaxWidth()
                .padding(vertical = paddingVertical),
            horizontalArrangement = Arrangement.Absolute.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TableCell(
                text = InputHelper.labelTable1Col1,
                weight = columnWeight[0],
                modifier = Modifier.padding(0.dp),
                alignment = TextAlign.Left,
                title = true
            )
            TableCell(
                text = InputHelper.labelTable1Col2,
                modifier = Modifier.padding(0.dp),
                weight = columnWeight[1],
                title = true
            )
            TableCell(
                text = InputHelper.labelTable1Col3,
                weight = columnWeight[2],
                modifier = Modifier.padding(0.dp),
                title = true
            )
            TableCell(
                modifier = Modifier.padding(0.dp),
                text = InputHelper.labelTable1Col4,
                weight = columnWeight[3],
                title = true
            )
            TableCell(
                modifier = Modifier.padding(0.dp),
                text = InputHelper.labelTable1Col5,
                weight = columnWeight[4],
                alignment = TextAlign.Right,
                title = true
            )
        }
        Divider(
            color = Color.LightGray, modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
        )

        List(5) { "G${it + 1}" }.forEachIndexed { index, title ->
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = paddingVertical),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TableCell(
                    text = title,
                    weight = columnWeight[0],
                    alignment = TextAlign.Left
                )
                TableCell(
                    text = calculator.G[index].toString(),
                    weight = columnWeight[1],
                    color = MaterialTheme.colorScheme.secondary,
                )
                TableCell(
                    text = "%.2f".format(calculator.membershipAlpha[index]),
                    color = MaterialTheme.colorScheme.primary,
                    weight = columnWeight[2]
                )
                TableCell(
                    text = calculator.T[index].toString(),
                    color = MaterialTheme.colorScheme.secondary,
                    weight = columnWeight[3],
                )
                TableCell(
                    text = "%.2f".format(calculator.membershipAlphaDesired[index]),
                    color = MaterialTheme.colorScheme.primary,
                    weight = columnWeight[4],
//                    alignment = TextAlign.Right
                )
            }
            Divider(
                color = Color.LightGray, modifier = Modifier
                    .height(1.dp)
//                    .fillMaxHeight()
                    .fillMaxWidth()
            )
        }
    }
}
