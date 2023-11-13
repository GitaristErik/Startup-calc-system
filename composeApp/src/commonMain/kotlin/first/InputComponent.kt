package first

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.ListInputsComponent

@Composable
fun InputComponent(
    stateMap: MutableMap<String, MutableState<List<Int>>>,

    ) {

    // ----------
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.Bottom
    ) {

        ListInputsComponent(
            state = stateMap["G"]!!,
            label = InputHelper.labelG,
            modifier = Modifier
//                .padding(all = 8.dp)
                .weight(.3f),
            arrayLabels = InputHelper.arrayLabelsG
        )

        ListInputsComponent(
            state = stateMap["T"]!!,
            label = InputHelper.labelT,
            modifier = Modifier
//                .padding(all = 8.dp)
//                .align(Alignment.Bottom)
                .weight(.125f)
        )
    }

    // ----------
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(
            stateMap["A"]!!,
            stateMap["B"]!!,
            stateMap["U"]!!,
            stateMap["P"]!!
        )
            .zip(
                listOf(
                    InputHelper.labelA,
                    InputHelper.labelB,
                    InputHelper.labelU,
                    InputHelper.labelP
                )
            )
            .forEach { (state, label) ->
                ListInputsComponent(
                    state = state,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                        .weight(1f),
                    label = label
                )
            }
    }
}