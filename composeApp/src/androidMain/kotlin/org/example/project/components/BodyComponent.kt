package org.example.project.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import utils.Criteria

@Composable
fun BodyComponent(
    state: MutableState<List<Criteria>>,
) {
    val criteria = when (state.value.first()) {
        is Criteria.OS,
        is Criteria.NFC,
        is Criteria.Processor ->
            state.value.chunked(2)

        else ->
            state.value.chunked(4)
    }

    criteria.forEachIndexed { index, list ->
        PrintCriteria(criteria = list) { i, c ->
            state.value = state.value.toMutableList().apply { set(i + index, c) }
        }
    }
}


@Composable
private fun PrintCriteria(
    modifier: Modifier = Modifier,
    criteria: List<Criteria>,
    onChanges: (Int, Criteria) -> Unit = { _, _ -> }
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(0.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val corner = 8.dp
        val leftCornerShape =
            RoundedCornerShape(topStart = corner, bottomStart = corner)
        val rightCornerShape = RoundedCornerShape(topEnd = corner, bottomEnd = corner)
        val centerCornerShape = RoundedCornerShape(0)

        criteria.forEachIndexed { i, it ->

            CriteriaComponent(
                modifier = Modifier
                    .padding(end = if (i != criteria.size - 1) 8.dp else 0.dp)
                    .weight(1f),
                isOutlined = true,
                value = it,
                onChanges = { onChanges(i, it) },
                cornerShape = if (criteria.size == 1) RoundedCornerShape(8.dp) else when (i) {
                    0 -> leftCornerShape
                    criteria.size - 1 -> rightCornerShape
                    else -> centerCornerShape
                }
            )
        }
    }
}


@Preview
@Composable
fun BodyComponentPreview() {
    val state = remember { mutableStateOf(Criteria.NFC) }
    BodyComponent(
        state = remember {
            mutableStateOf(
                listOf(
                    Criteria.NFC.Yes,
                    Criteria.NFC.Yes,
                    Criteria.NFC.Yes, Criteria.NFC.Yes
                )
            )
        }
    )
}