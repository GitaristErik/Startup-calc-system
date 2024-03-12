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
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
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
    localizedName: String,
    state: MutableDoubleState,
    stateWeight: MutableState<Int>
) {
    Row(
        modifier = modifier
//            .fillMaxHeight()
            .fillMaxWidth()
            .padding(top = 32.dp),
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


        InputNumberComponent(
            value = c,
            modifier = Modifier.weight(0.7f),
            prefix = prefixT,
            outlined = false,
            shape = cornerShape,
            label = localizedName,
            onChange = { state.doubleValue = it }
        )
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
                localizedName = Criteria.OS.Android13.localizedName,
                state = mutableDoubleStateOf(1.0),
                stateWeight = mutableIntStateOf(1)
            )
            TitleComponent(
                index = 1,
                localizedName = Criteria.Processor.MediaTekDimensity9000.localizedName,
                state = mutableDoubleStateOf(2.0),
                stateWeight = mutableIntStateOf(2)
            )
            TitleComponent(
                index = 2,
                localizedName = Criteria.NFC.Yes.localizedName,
                state = mutableDoubleStateOf(3.0),
                stateWeight = mutableIntStateOf(3)
            )
            TitleComponent(
                index = 3,
                localizedName = Criteria.Processor.MediaTekDimensity9000.localizedName,
                state = mutableDoubleStateOf(4.0),
                stateWeight = mutableIntStateOf(4)
            )
        }
    }
}