package org.example.project.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.LabelWithIndexes
import components.TwoInputsAndMenuComponent
import utils.ThirdAlgorithm

@Composable
fun KComponent(
    index: Int,
    key: String,
    state: MutableState<Triple<ThirdAlgorithm.L?, Double?, Int>>,
    modifier: Modifier = Modifier,
) {
    TwoInputsAndMenuComponent(
        state, modifier = modifier,//.padding(horizontal = 8.dp),
        prefix = {
            LabelWithIndexes(
                label = "K",
                indexTop = when (key) {
                    "leaders", "teams" -> "2"
                    else -> key
                },
                indexBottom = when (key) {
                    "teams" -> index + 3
                    else -> index
                }.toString(),
                suffix = "= ",
            )
        }, horizontalSpace = 4.dp
    )
}