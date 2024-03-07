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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.InputNumberComponent
import components.LabelWithIndexes
import utils.Criteria


@Composable
fun TitleComponent(
    modifier: Modifier = Modifier,
    index: Int,
    state: MutableState<Criteria>,
    stateWeight: MutableState<Int>
) {
    Row(
        modifier = modifier
//            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val c by state

        val cornerShape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
        val leftCornerShape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)

        val prefixA = @Composable {
            LabelWithIndexes(
                label = "a",
                indexTop = "",
                indexBottom = (index + 1).toString(),
                suffix = "= ",
            )
        }


        InputNumberComponent(
            value = stateWeight.value,
            charLimit = 3,
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(.3f),
            onChange = { stateWeight.value = it },
            outlined = false, shape = leftCornerShape, prefix = prefixA,
        )


        val prefixT = @Composable {
            LabelWithIndexes(
                label = "T",
                indexTop = "",
                indexBottom = (index + 1).toString(),
                suffix = "= ",
            )
        }


        CriteriaComponent(
            modifier = Modifier.weight(0.7f),
            prefix = prefixT,
            isOutlined = false,
            cornerShape = cornerShape,
            hint = c.localizedName,
            value = c
        ) {
            state.value = it
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(heightDp = 512)
@Composable
fun TitleComponentPreview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TitleComponent(
                index = 0,
                state = mutableStateOf(Criteria.OS.Android13),
                stateWeight = mutableIntStateOf(1)
            )
            TitleComponent(
                index = 1,
                state = mutableStateOf(Criteria.Processor.MediaTekDimensity9000),
                stateWeight = mutableIntStateOf(2)
            )
            TitleComponent(
                index = 2,
                state = mutableStateOf(Criteria.NFC.Yes),
                stateWeight = mutableIntStateOf(3)
            )
            TitleComponent(
                index = 3,
                state = mutableStateOf(Criteria.AccumulatorCapacity(4500)),
                stateWeight = mutableIntStateOf(4)
            )
            TitleComponent(
                index = 4,
                state = mutableStateOf(Criteria.DisplayDiagonal(6.7)),
                stateWeight = mutableIntStateOf(5)
            )
        }
    }
}