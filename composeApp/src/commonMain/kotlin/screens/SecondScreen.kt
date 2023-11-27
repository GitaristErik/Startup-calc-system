package screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.LabelWithIndexes
import components.ListInputAndMenuComponent
import components.TableColumn
import components.TableComponent
import components.TableOneRow
import second.StringsData
import utils.SecondAlgorithm
import utils.SecondAlgorithm.L

@Composable
fun SecondScreen() {

    var stateCalcPressed by remember { mutableStateOf(false) }

    val stateInputData: List<List<MutableState<Pair<L, Double>>>> = StringsData.K.map { list ->
        list.map { (l, d) -> remember { mutableStateOf(l to d) } }
    }.onEachIndexed { _, list ->
        list.forEach { state ->
            LaunchedEffect(state.value) { stateCalcPressed = false }
        }
    }

    Row {
        stateInputData.forEachIndexed { index, list ->
            Column(
                Modifier.weight(0.1f),
            ) {
                ListInputAndMenuComponent(
                    list,
                    label = StringsData.labelsK[index].second,
                    prefix = {
                        LabelWithIndexes(
                            label = "K",
                            indexTop = StringsData.labelsK[index].first,
                            indexBottom = "${it + 1}"
                        )
                    }
                )
            }
        }

    }


    val makeSpacer: @Composable () -> Unit = { Spacer(modifier = Modifier.height(16.dp)) }
    val makeSpacer2: @Composable () -> Unit = { Spacer(modifier = Modifier.height(32.dp)) }

    makeSpacer()
    FilledTonalButton(
        onClick = { stateCalcPressed = true }, enabled = !stateCalcPressed
    ) {
        Text(text = first.StringsData.labelCalculate)
    }
    makeSpacer()

    if (!stateCalcPressed) return

    val calculator = SecondAlgorithm(
        K = stateInputData.map { list -> list.map { it.value } }
    )

    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.Top,
    ) {
        Column(
            Modifier.padding(horizontal = 8.dp).weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            TableComponent(
                tableColumns = listOf(
                    TableColumn(
                        StringsData.labelTable1Col1,
                        0.15f,
                        TextAlign.Left,
                        fontStyle = FontStyle.Italic
                    ),
                    TableColumn(
                        StringsData.labelTable1Col2,
                        0.15f,
                        TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary,
                    ),
                    TableColumn(
                        StringsData.labelTable1Col3,
                        0.1f,
                        TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    ),
                ),
                items = StringsData.labelsK.mapIndexed { index, it ->
                    listOf(
                        "K[${it.first}] - ${it.second}",
                        calculator.resultingTermEstimate[index].title,
                        "%.2f".format(calculator.aggregatedScore[index])
                    )
                },
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }

        Column(
            Modifier.padding(horizontal = 8.dp).weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            TableOneRow(
                items = StringsData.labelsK.zip(calculator.generalisedRiskAssessment)
                    .associate { (label, value) ->
                        "x ${label.first}" to "%.2f".format(value)
                    }, tableLabel = StringsData.labelTable2
            )
        }
    }

    makeSpacer2()

    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = StringsData.labelAggregatedScore,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W500,
                textAlign = TextAlign.Center,
//            modifier = Modifier.weight(.2f)
            )

            Text(
                text = "%.2f".format(calculator.aggregatedScoreRisc),
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W400,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier
//                .weight(.1f)
                    .widthIn(min = 128.dp).padding(horizontal = 16.dp, vertical = 8.dp).background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.extraLarge
                    ),
            )
        }

        Column(
            Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Text(
                text = StringsData.result,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W500
            )

            Text(
                text = calculator.linguisticInterpretation,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.surfaceTint,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                    .widthIn(min = 320.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = MaterialTheme.shapes.extraLarge
                    ),
            )
        }
    }

    makeSpacer2()
}
