package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ListInputsComponent(
    state: MutableState<List<Int>>,
    modifier: Modifier = Modifier,
    label: String? = null,
    arrayLabels: List<String>? = null
) {
    Column(
        modifier = modifier,
//        userScrollEnabled = false,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
//        item {
        label?.let {
            Text(
                text = it,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
//        }

//        items(state.value.size) { index ->
        state.value.forEachIndexed { index, _ ->
            InputNumberComponent(
                modifier = Modifier.fillMaxWidth(),
                value = state.value[index],
                onChange = {
                    state.value = state.value.mapIndexed { i, value ->
                        if (i == index) it else value
                    }
                },
                label = arrayLabels?.get(index) ?: ""
            )
//        }
        }
    }
}