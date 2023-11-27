package third

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.InputNumberComponent
import utils.ThirdAlgorithm


@Composable
fun TitleComponent(
    key: String,
    state: MutableState<Triple<ThirdAlgorithm.L?, Double?, Int>>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        val cornerRound = 10.dp

        InputNumberComponent<Int>(
            value = state.value.third,
            onChange = { state.value = with(state.value) { Triple(first, second, it) } },
            charLimit = 2,
            modifier = Modifier
                .weight(.25f)
                .fillMaxHeight(),
            outlined = false,
            shape = RoundedCornerShape(topStart = cornerRound, bottomEnd = cornerRound),
        )

        Text(
            text = StringsData.labelsK[key] ?: "",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .weight(.75f)
                .fillMaxHeight(),
        )
    }
}
