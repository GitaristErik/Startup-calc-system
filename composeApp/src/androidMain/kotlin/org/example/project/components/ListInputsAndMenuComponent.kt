package org.example.project.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
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

    stateInputData.forEach { (key, listStates) ->
        Column(
//            modifier = modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.Center
        ) {
            listStates.forEachIndexed { index, state ->
                with(state.value) {
                    if (first == null && second == null) {
                        if (key == "2" || key == "3") Divider(
                            Modifier
                                .padding(top = 24.dp, bottom = 8.dp)
                                .fillMaxWidth()
                                .height(1.dp)
                        )
                        TitleComponent(
                            key, state, modifier.padding(top = if (key == "1") 0.dp else 24.dp)
                        )
                    } else {
                        KComponent(index, key, state, modifier)
                    }
                }
            }
        }
    }
}
