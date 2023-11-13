package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Bottom
import androidx.compose.ui.Modifier
import components.*
import androidx.compose.ui.unit.dp
import first.InputHelper


@Composable
fun FirstScreen() {
    val arrayStateG = remember {
        mutableStateOf(
            InputHelper.arrayG
        )
    }
    val arrayStateA = remember {
        mutableStateOf(
            InputHelper.arrayA
        )
    }
    val arrayStateB = remember {
        mutableStateOf(
            InputHelper.arrayB
        )
    }
/*
    LazyRow(
        modifier = Modifier.padding(all = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        itemsIndexed(
            listOf(
                arrayStateG,
                arrayStateA,
                arrayStateB,
            ).zip(
                listOf(
                    InputHelper.labelG,
                    InputHelper.labelA,
                    InputHelper.labelB,
                )
            )
        ) { index, (state, label) ->
            ListInputsComponent(
                state = state,
                label = label,
                modifier = if (index == 0) Modifier.width(256.dp) else Modifier.width(128.dp),
                arrayLabels = if (index == 0) InputHelper.arrayLabelsG else null
            )
        }
    }*/

    // ----------
    Row(modifier = Modifier.padding(all = 8.dp),
        horizontalArrangement = Arrangement.Center) {

        val arrayStateT = remember {
            mutableStateOf(
                InputHelper.arrayT
            )
        }
        val arrayStateU = remember {
            mutableStateOf(
                InputHelper.arrayU
            )
        }
        val arrayStateP = remember {
            mutableStateOf(
                InputHelper.arrayU
            )
        }

        listOf(
            arrayStateT,
            arrayStateU,
            arrayStateP
        ).zip(
            listOf(
                InputHelper.labelT,
                InputHelper.labelU,
                InputHelper.labelP
            )
        ).forEachIndexed { index, (state, label) ->
            ListInputsComponent(
                state = state,
                modifier = Modifier.width(128.dp).align(Bottom).padding(all = 4.dp),
                label = label
            )
        }
    }
}