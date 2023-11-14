package org.example.project.first

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
import first.StringsData

@Composable
fun InputComponent(
    stateMap: MutableMap<String, MutableState<List<Int>>>,

) {

    ListInputsComponent(
        state = stateMap["G"]!!,
        label = StringsData.labelG,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 8.dp),
        arrayLabels = StringsData.arrayLabelsG
    )

    // ----------
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {

        ListInputsComponent(
            state = stateMap["A"]!!,
            label = StringsData.labelA,
            modifier = Modifier
                .align(Alignment.Bottom)
                .weight(1f)
        )
        ListInputsComponent(
            state = stateMap["B"]!!,
            label = StringsData.labelB,
            modifier = Modifier
                .align(Alignment.Bottom)
                .weight(1f)
        )
    }

    // ----------
    Row(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        listOf(stateMap["T"]!!, stateMap["U"]!!, stateMap["P"]!!)
            .zip(listOf(StringsData.labelT, StringsData.labelU, StringsData.labelP))
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