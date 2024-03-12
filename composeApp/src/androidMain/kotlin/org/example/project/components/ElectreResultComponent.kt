package org.example.project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import second.StringsData
import utils.ThirdAlgorithm

@Composable
fun ElectreResultComponent(
    kernels: List<String>,
    data: List<String>,
    setShadow: @Composable Modifier.() -> Modifier = { Modifier },
) {

    val rate = ThirdAlgorithm.Y_LINGUISTIC.MEDIUM_HIGH

    Spacer(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(16.dp)
    )

    Text(
        text = StringsData.labelResult,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.W500,
    )


    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.primaryContainer,
                MaterialTheme.shapes.extraLarge
            ),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = kernels.joinToString(separator = ", "),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.W400,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.surfaceTint,
            modifier = Modifier
//                .weight(.1f)
                .widthIn(min = 128.dp)
                .padding(horizontal = 32.dp, vertical = 32.dp)
                .background(
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = MaterialTheme.shapes.extraLarge
                ),
        )
    }


    Column(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.tertiaryContainer,
                MaterialTheme.shapes.extraLarge
            ),
//        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = StringsData.labelResult2,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )

        Surface(
            modifier = setShadow(
                Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 4.dp,
                    bottom = 32.dp
                )
            ),
            color = rate.color,
            shape = MaterialTheme.shapes.extraLarge,
//        shadowElevation = 1.dp,
//        tonalElevation = 10.dp
        ) {
            Text(
                text = data.joinToString(separator = ", "),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = rate.textColor,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }
}
