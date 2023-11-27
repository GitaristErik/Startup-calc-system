package components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import utils.SecondAlgorithm


@Composable
fun ListInputAndMenuComponent(
    stateInputData: List<MutableState<Pair<SecondAlgorithm.L, Double>>>,
    modifier: Modifier = Modifier,
    label: String? = null,
    prefix: @Composable ((Int) -> Unit)? = null,
) {
    label?.let { title ->
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            modifier = modifier.padding(top = 16.dp),
        )
    }

    stateInputData.forEachIndexed { index, state ->
        InputAndMenuComponent(
            state,
            modifier = modifier.padding(horizontal = 8.dp),
            prefix = prefix?.let { { it.invoke(index) } },
            horizontalSpace = 4.dp
        )
    }
}
