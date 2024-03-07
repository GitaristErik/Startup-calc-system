package org.example.project.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.LabelWithIndexes
import utils.Criteria


@Composable
fun TitleComponent(
    modifier: Modifier = Modifier,
    index: Int,
    state: MutableState<Criteria>,
) {
    Row(
        modifier = modifier
//            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val c by state

        Text(
            text = state.value.localizedName,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            softWrap = true,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .padding(end = 8.dp)
//                .weight(0.5f)
//                .fillMaxHeight(),
        )


        val prefix = @Composable {
            LabelWithIndexes(
                label = "T",
                indexTop = "",
                indexBottom = (index + 1).toString(),
                suffix = "= ",
            )
        }

        val cornerShape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)

        CriteriaComponent(
            prefix = prefix,
            isOutlined = false,
            cornerShape = cornerShape,
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
            )
            TitleComponent(
                index = 1,
                state = mutableStateOf(Criteria.Processor.MediaTekDimensity9000),
            )
            TitleComponent(
                index = 2,
                state = mutableStateOf(Criteria.NFC.Yes),
            )
            TitleComponent(
                index = 3,
                state = mutableStateOf(Criteria.AccumulatorCapacity(4500)),
            )
            TitleComponent(
                index = 4,
                state = mutableStateOf(Criteria.DisplayDiagonal(6.7))
            )
        }
    }
}