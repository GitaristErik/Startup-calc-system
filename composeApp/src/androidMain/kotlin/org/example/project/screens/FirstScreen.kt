package org.example.project.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.TableOneRow
import first.StringsData
import first.TableFirstLevel
import first.TableSecondLevel
import org.example.project.first.InputComponent
import utils.FirstAlgorithm
import utils.FirstAlgorithm.M


@Composable
fun FirstScreen() {

    var stateCalcPressed by remember { mutableStateOf(false) }

    val stateMapInputData = mapOf(
        "G" to StringsData.arrayG,
        "A" to StringsData.arrayA,
        "B" to StringsData.arrayB,
        "T" to StringsData.arrayT,
        "U" to StringsData.arrayU,
        "P" to StringsData.arrayP
    ).mapValues {
        remember { mutableStateOf(it.value) }
    }.toMutableMap().onEach { (_, v) ->
        LaunchedEffect(v.value) { stateCalcPressed = false }
    }

    InputComponent(stateMap = stateMapInputData)

    FilledTonalButton(
        onClick = { stateCalcPressed = true },
        enabled = !stateCalcPressed
    ) {
        Text(text = StringsData.labelCalculate)
    }

//    PrintInputs(stateMap = stateMapInputData)

    if (!stateCalcPressed) return

    Spacer(modifier = Modifier.height(16.dp))

    val calculator = FirstAlgorithm(
        G = stateMapInputData["G"]!!.value,
        A = stateMapInputData["A"]!!.value,
        B = stateMapInputData["B"]!!.value,
        T = stateMapInputData["T"]!!.value,
        alphaU = stateMapInputData["U"]!!.value,
        P = stateMapInputData["P"]!!.value
    )

    TableFirstLevel(
        calculator,
        titleTypography = MaterialTheme.typography.titleSmall,
        bodyTypography = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    TableSecondLevel(
        calculator,
        titleTypography = MaterialTheme.typography.titleSmall,
        bodyTypography = MaterialTheme.typography.bodyMedium
    )

    Spacer(modifier = Modifier.height(16.dp))

    TableOneRow(
        items = calculator.scoresByCriterias.mapIndexed { index, item ->
            "G${index + 1}" to "%.2f".format(item)
        }.toMap(),
        tableLabel = StringsData.labelTable3
    )

    Spacer(modifier = Modifier.height(16.dp))

    TableOneRow(
        items = calculator.normalizedP.mapIndexed { index, item ->
            "P${index + 1}" to "%.2f".format(item)
        }.toMap(),
        tableLabel = StringsData.labelTable4
    )

    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Text(
            text = "${StringsData.aggregatedScore}: ",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
        )
        Text(
            text = "%.2f".format(calculator.aggregatedScore),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.W500,
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Text(
            text = "${StringsData.result}: ",
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
        )


        val color = when (calculator.ratedScale) {
            M.MEDIUM -> Color(0xfff8deb5)
            M.HIGH, M.VERY_HIGH -> Color(0xffadf7a4)
            M.VERY_LOW, M.LOW -> Color(0xffffcccf)
        }
        val textColor = when (calculator.ratedScale) {
            M.MEDIUM -> Color(0xffde7a1d)
            M.HIGH, M.VERY_HIGH -> Color(0xff00ad0e)
            M.VERY_LOW, M.LOW -> Color(0xffca1e17)
        }

        Text(
            text = calculator.ratedScale.title,
            color = textColor,
            modifier = Modifier
                .padding(16.dp)
                .background(color, shape = RoundedCornerShape(50.dp)),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.W500,
        )
    }

}


@Composable
fun PrintInputs(stateMap: MutableMap<String, MutableState<List<Int>>>) {
    stateMap.forEach {
        Text(text = "${it.key} = ${it.value.value}")
    }
}