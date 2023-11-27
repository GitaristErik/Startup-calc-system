package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Top
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import third.KComponent
import third.TitleComponent
import utils.ThirdAlgorithm


@Composable
fun ListInputsAndMenuComponent(
    stateInputData: Map<String, List<MutableState<Triple<ThirdAlgorithm.L?, Double?, Int>>>>,
    modifier: Modifier = Modifier,
) {

    val printFields: @Composable ColumnScope.(String) -> Unit = { key: String ->
        stateInputData[key]?.let { listStates ->
            listStates.forEachIndexed { index, state ->
                with(state.value) {
                    if (first == null && second == null) {
                        TitleComponent(key, state, modifier)
                    } else {
                        KComponent(index, key, state, modifier)
                    }
                }
            }
        }
    }


    Row(
        verticalAlignment = Top,
        horizontalArrangement = Arrangement.Center,
        modifier = modifier.padding(top = 32.dp).fillMaxWidth()
    ) {

        Column(
//            verticalArrangement = Arrangement.spacedBy(32.dp),
            modifier = Modifier.weight(.5f),
            horizontalAlignment = CenterHorizontally
        ) {
            printFields("1")
            Spacer(modifier = Modifier.height(32.dp))
            printFields("3")
        }


        Column(
//            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.weight(.5f),
            horizontalAlignment = CenterHorizontally
        ) {
            printFields("2")
            Spacer(modifier = Modifier.height(32.dp))
            printFields("leaders")
            Spacer(modifier = Modifier.height(16.dp))
            printFields("teams")
        }
    }
}
