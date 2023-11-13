package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.TableOneRow
import first.InputComponent
import first.InputHelper
import first.TableFirstLevel
import first.TableSecondLevel
import utils.FirstAlgorithm


@Composable
fun FirstScreen() {

    var stateCalcPressed by remember { mutableStateOf(false) }

    val stateMapInputData = mapOf(
        "G" to InputHelper.arrayG,
        "A" to InputHelper.arrayA,
        "B" to InputHelper.arrayB,
        "T" to InputHelper.arrayT,
        "U" to InputHelper.arrayU,
        "P" to InputHelper.arrayP
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
        Text(text = InputHelper.labelCalculate)
    }

//    PrintInputs(stateMap = stateMapInputData)

    if (!stateCalcPressed) return

    val spacerWidth = 16.dp
    val spacerHeight = 16.dp
    Spacer(modifier = Modifier.height(spacerHeight))

    val calculator = FirstAlgorithm(
        G = stateMapInputData["G"]!!.value,
        A = stateMapInputData["A"]!!.value,
        B = stateMapInputData["B"]!!.value,
        T = stateMapInputData["T"]!!.value,
        alphaU = stateMapInputData["U"]!!.value,
        P = stateMapInputData["P"]!!.value
    )

    TableFirstLevel(calculator)

    Spacer(modifier = Modifier.height(spacerHeight))

    TableSecondLevel(calculator)

    Spacer(modifier = Modifier.height(spacerHeight))

    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceAround,
    ) {

        Column(modifier = Modifier.weight(1f)) {
            TableOneRow(
                items = calculator.scoresByCriterias.mapIndexed { index, item ->
                    "G${index + 1}" to "%.2f".format(item)
                }.toMap(),
                tableLabel = InputHelper.labelTable3
            )
        }

        Column(modifier = Modifier.weight(1f)) {
            TableOneRow(
                items = calculator.normalizedP.mapIndexed { index, item ->
                    "P${index + 1}" to "%.2f".format(item)
                }.toMap(),
                tableLabel = InputHelper.labelTable4
            )
        }
    }

    Spacer(modifier = Modifier.height(spacerHeight))

    Row(
        horizontalArrangement = Arrangement.SpaceAround,
    ) {

        val weightList = listOf(.25f, .1f, .15f, .3f)
        Text(
            text = "${InputHelper.aggregatedScore}: ",
            modifier = Modifier.padding(16.dp).weight(weightList[0]),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
        )
        Text(
            text = "%.2f".format(calculator.aggregatedScore),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(16.dp).weight(weightList[1]),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Left,
            fontWeight = FontWeight.W500,
        )

        Spacer(modifier = Modifier.width(spacerWidth))

        Text(
            text = "${InputHelper.result}: ",
            modifier = Modifier.padding(16.dp).weight(weightList[2]),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Right,
            fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
        )


        val color = when (calculator.ratedScale) {
            FirstAlgorithm.M.MEDIUM -> Color(0xfff8deb5)
            FirstAlgorithm.M.HIGH, FirstAlgorithm.M.VERY_HIGH -> Color(0xffadf7a4)
            FirstAlgorithm.M.VERY_LOW, FirstAlgorithm.M.LOW -> Color(0xffffcccf)
        }
        val textColor = when (calculator.ratedScale) {
            FirstAlgorithm.M.MEDIUM -> Color(0xffde7a1d)
            FirstAlgorithm.M.HIGH, FirstAlgorithm.M.VERY_HIGH -> Color(0xff00ad0e)
            FirstAlgorithm.M.VERY_LOW, FirstAlgorithm.M.LOW -> Color(0xffca1e17)
        }

        Text(
            text = calculator.ratedScale.title,
            color = textColor,
            modifier = Modifier
                .padding(16.dp)
                .background(color, shape = RoundedCornerShape(50.dp))
                .weight(weightList[3]),
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.W500,
        )
    }

}