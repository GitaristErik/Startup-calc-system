package third

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import utils.ThirdAlgorithm

@Composable
fun ColumnScope.DePhasingResultComponent(
    teamRate: ThirdAlgorithm.Y_LINGUISTIC,
    setShadow: @Composable Modifier.() -> Modifier = { Modifier },
) {


    Text(
        text = StringsData.labelResult,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.W500,
    )

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
            text = ThirdAlgorithm.Y_LINGUISTIC.prefix,
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
            color = teamRate.color,
            shape = MaterialTheme.shapes.extraLarge,
//        shadowElevation = 1.dp,
//        tonalElevation = 10.dp
        ) {
            Text(
                text = teamRate.title,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = teamRate.textColor,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 16.dp)
            )
        }
    }
}
