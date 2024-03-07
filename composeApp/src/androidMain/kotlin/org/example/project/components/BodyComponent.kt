package org.example.project.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
            .padding(8.dp),
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

@SuppressLint("UnrememberedMutableState")
@Preview(heightDp = 1400)
@Composable
fun BodyComponentPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TitleComponent(index = 0, state = mutableStateOf(Criteria.OS.Android13))
            BodyComponent(
                state = mutableStateOf(
                    listOf(
                        Criteria.OS.Android13,
                        Criteria.OS.Android13,
                        Criteria.OS.Android12,
                        Criteria.OS.IOS16,
                    )
                )
            )
            TitleComponent(
                index = 1,
                state = mutableStateOf(Criteria.Processor.MediaTekDimensity9000),
            )
            BodyComponent(
                state = mutableStateOf(
                    listOf(
                        Criteria.Processor.AppleA16Bionic,
                        Criteria.Processor.Snapdragon8Gen1,
                        Criteria.Processor.Exynos1280,
                        Criteria.Processor.Snapdragon7Gen1,
                    )
                )
            )
            TitleComponent(
                index = 2,
                state = mutableStateOf(Criteria.NFC.Yes),
            )
            BodyComponent(
                state = mutableStateOf(
                    listOf(
                        Criteria.NFC.Yes,
                        Criteria.NFC.Yes,
                        Criteria.NFC.Yes,
                        Criteria.NFC.Yes
                    )
                )
            )

            TitleComponent(
                index = 3,
                state = mutableStateOf(Criteria.AccumulatorCapacity(4500)),
            )
            BodyComponent(
                state = mutableStateOf(
                    listOf(
                        Criteria.AccumulatorCapacity(4500),
                        Criteria.AccumulatorCapacity(4500),
                        Criteria.AccumulatorCapacity(4500),
                        Criteria.AccumulatorCapacity(4500),
                    )
                )
            )
            TitleComponent(
                index = 4,
                state = mutableStateOf(Criteria.DisplayDiagonal(6.7))
            )
            BodyComponent(
                state = mutableStateOf(
                    listOf(
                        Criteria.DisplayDiagonal(6.7),
                        Criteria.DisplayDiagonal(6.7),
                        Criteria.DisplayDiagonal(6.7),
                        Criteria.DisplayDiagonal(6.7),
                    )
                )
            )
        }
    }
}