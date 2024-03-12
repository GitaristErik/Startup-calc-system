package org.example.project.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableDoubleState
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import components.InputNumberComponent
import components.LabelWithIndexes
import second.StringsData
import utils.Criteria

@Composable
fun TitleComponent2(
    modifier: Modifier = Modifier,
    index: Int,
    localizedName: String,
    state: MutableDoubleState,
    stateVeto: MutableDoubleState,
    stateWeight: MutableIntState
) {

    val cornerShape = RoundedCornerShape(topEnd = 10.dp, bottomEnd = 10.dp)
    val leftCornerShape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)

    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),
        color = MaterialTheme.colorScheme.outlineVariant
    )

    Row(
        modifier = modifier
            .fillMaxWidth(),
//            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .weight(.7f)
                .padding(start = 8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = StringsData.labelVeto,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Left,
            )
            Text(
                text = StringsData.labelVetoExtra,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.outline,
                textAlign = TextAlign.Left,
            )
        }


        val prefixV = @Composable {
            LabelWithIndexes(
                label = "v",
                indexTop = "",
                indexBottom = (index + 1).toString(),
                suffix = "= ",
            )
        }

        InputNumberComponent(
            value = stateVeto.doubleValue,
            modifier = Modifier.weight(0.3f),
            prefix = prefixV,
            outlined = false,
            shape = cornerShape,
            onChange = { stateVeto.doubleValue = it }
        )
    }

    Row(
        modifier = modifier
//            .fillMaxHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        val prefixA = @Composable {
            LabelWithIndexes(
                label = "w",
                indexTop = "",
                indexBottom = (index + 1).toString(),
                suffix = "= ",
            )
        }


        InputNumberComponent(
            value = stateWeight.intValue,
            charLimit = 3,
            label = "Труш",
            modifier = Modifier
                .padding(end = 8.dp)
                .weight(.36f)
                .wrapContentWidth(),
            onChange = { stateWeight.intValue = it },
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
            value = state.doubleValue,
            modifier = Modifier.weight(0.7f),
            prefix = prefixT,
            outlined = false,
            shape = cornerShape,
            label = localizedName,
            onChange = { state.doubleValue = it }
        )
    }

    /*    Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            color = MaterialTheme.colorScheme.outline
        )*/
}

@SuppressLint("UnrememberedMutableState")
@Preview(heightDp = 512)
@Composable
fun TitleComponent2Preview() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TitleComponent2(
                index = 0,
                localizedName = Criteria.OS.Android13.localizedName,
                state = mutableDoubleStateOf(1.0),
                stateWeight = mutableIntStateOf(1),
                stateVeto = mutableDoubleStateOf(1.0)
            )
            TitleComponent2(
                index = 1,
                localizedName = Criteria.Processor.MediaTekDimensity9000.localizedName,
                state = mutableDoubleStateOf(2.0),
                stateWeight = mutableIntStateOf(2),
                stateVeto = mutableDoubleStateOf(2.0)
            )
            TitleComponent2(
                index = 2,
                localizedName = Criteria.NFC.Yes.localizedName,
                state = mutableDoubleStateOf(3.0),
                stateWeight = mutableIntStateOf(3),
                stateVeto = mutableDoubleStateOf(3.0)
            )
            TitleComponent2(
                index = 3,
                localizedName = Criteria.Processor.MediaTekDimensity9000.localizedName,
                state = mutableDoubleStateOf(4.0),
                stateWeight = mutableIntStateOf(4),
                stateVeto = mutableDoubleStateOf(4.0)
            )
        }
    }
}