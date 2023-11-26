package third

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.CalculatedNeuroComponent(calculatedValue: Double) {

    Text(
        text = StringsData.labelCalculatedNeuro,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.W500,
        textAlign = TextAlign.Center,
//            modifier = Modifier.weight(.2f)
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
            text = "%.4f".format(calculatedValue),
            style = MaterialTheme.typography.headlineSmall,
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
}
