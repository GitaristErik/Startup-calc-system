package org.example.project.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.BeeTablesCompose
import components.LabelWithIndexes
import components.TableComponent
import first.StringsData
import org.example.project.components.BodyComponent
import org.example.project.components.TitleComponent
import utils.AlgorithmFirst
import utils.Convolution
import utils.Convolution.AverageConvolution.transpose
import utils.Criteria


@Composable
fun FirstScreen() {

    var stateCalcPressed by remember { mutableStateOf(false) }

    val (stateInputDataT,
        stateInputDataA,
        stateInputDataListK) = listOf(
        Criteria.Price.defaultDataSet,
        Criteria.OS.defaultDataSet,
        Criteria.Processor.defaultDataSet,
        Criteria.Ram.defaultDataSet,
        Criteria.Storage.defaultDataSet,
        Criteria.NFC.defaultDataSet,
        Criteria.AccumulatorCapacity.defaultDataSet,
        Criteria.CamerasCount.defaultDataSet,
        Criteria.DisplayDiagonal.defaultDataSet,
        Criteria.Rating.defaultDataSet,
        Criteria.Weight.defaultDataSet,
    ).map { (list, t, a) ->
        Triple(
            remember(list[0].localizedName) { mutableDoubleStateOf(t) },
            remember { mutableIntStateOf(a) },
            remember(list.hashCode()) { mutableStateOf(list) }
        )
    }.onEachIndexed { index, (t, a, k) ->
        LaunchedEffect(t.doubleValue) { stateCalcPressed = false }
        LaunchedEffect(a.intValue) { stateCalcPressed = false }
        TitleComponent(
            index = index,
            localizedName = k.value[0].localizedName,
            state = t,
            stateWeight = a
        )

        LaunchedEffect(k.value) { stateCalcPressed = false }
        BodyComponent(k)
    }.unzip3()


    Spacer(modifier = Modifier.height(16.dp))
    FilledTonalButton(
        onClick = { stateCalcPressed = true },
        enabled = !stateCalcPressed
    ) {
        Text(text = StringsData.labelCalculate)
    }

    if (!stateCalcPressed) return

    Spacer(modifier = Modifier.height(16.dp))

    val calculator = AlgorithmFirst(
        criteria = stateInputDataListK.map { it.value },
        weights = stateInputDataA.map { it.intValue.toDouble() },
        T = stateInputDataT.map { it.value }
    )

    Text(
        modifier = Modifier.padding(vertical = 16.dp),
        text = StringsData.labelTable1,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    BeeTablesCompose(
        data = calculator.normalizedCriteria.mapIndexed { index, list ->
            val i = list.map { "%.2f".format(it) }
            TableNormalizeData(
                index = "K ${index + 1}",
                x1 = i.getOrNull(0) ?: "",
                x2 = i.getOrNull(1) ?: "",
                x3 = i.getOrNull(2) ?: "",
                x4 = i.getOrNull(3) ?: ""
            )
        },
        headerTableTitles = listOf(" ") + List(4) { "x${it + 1}" },
        enableTableHeaderTitles = true,
        tableElevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        rowBorderColor = MaterialTheme.colorScheme.outlineVariant,
        rowTextStyle = MaterialTheme.typography.bodyMedium,
        headerTitlesBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        tableRowColors = with(MaterialTheme.colorScheme) { listOf(surface, background) },
        headerTitlesTextStyle = MaterialTheme.typography.titleLarge,
        headerTitlesBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
        textAlign = TextAlign.Center,
    )

    TableWeightNormalize(calculator.normalizedWeights)


    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        text = StringsData.labelTableFinal,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )


    val calculatedResults = Convolution::class.sealedSubclasses.mapNotNull {
        it.objectInstance?.calculateCriteria(
            normalizedO = calculator.normalizedCriteria,
            normalizedWeights = calculator.normalizedWeights
        )
    }
    val calculatedBest = Convolution::class.sealedSubclasses.mapIndexedNotNull { i, it ->
        it.objectInstance?.getBestAlternative(
            calculatedResults[i]
        )
    }
    TableResults(calculatedResults, calculatedBest)


    Text(
        modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
        text = StringsData.labelMethod2,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        text = StringsData.labelTable3,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    BeeTablesCompose(
        data = calculator.matrixZ.mapIndexed { index, list ->
            val i = list.map { "%.2f".format(it) }
            TableNormalizeData(
                index = "K ${index + 1}",
                x1 = i.getOrNull(0) ?: "",
                x2 = i.getOrNull(1) ?: "",
                x3 = i.getOrNull(2) ?: "",
                x4 = i.getOrNull(3) ?: ""
            )
        },
        headerTableTitles = listOf(" ") + List(4) { "x${it + 1}" },
        enableTableHeaderTitles = true,
        tableElevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        rowBorderColor = MaterialTheme.colorScheme.outlineVariant,
        rowTextStyle = MaterialTheme.typography.bodyMedium,
        headerTitlesBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        tableRowColors = with(MaterialTheme.colorScheme) { listOf(surface, background) },
        headerTitlesTextStyle = MaterialTheme.typography.titleLarge,
        headerTitlesBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
        textAlign = TextAlign.Center,
    )


    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        text = StringsData.labelTableFinal,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    val calculatedResults2 = Convolution::class.sealedSubclasses.mapNotNull {
        it.objectInstance?.calculateCriteria(
            normalizedO = calculator.matrixZ,
            normalizedWeights = calculator.normalizedWeights
        )
    }
    val calculatedBest2 = Convolution::class.sealedSubclasses.mapIndexedNotNull { i, it ->
        it.objectInstance?.getBestAlternative(
            calculatedResults[i]
        )
    }
    TableResults(calculatedResults2, calculatedBest2)



    Spacer(modifier = Modifier.height(56.dp))
}

data class TableNormalizeData(
    val index: String,
    val x1: String,
    val x2: String,
    val x3: String,
    val x4: String,
)

@Composable
private fun TableResults(calculatedResults: List<List<Double>>, best: List<Int>) {
    BeeTablesCompose(
        data = calculatedResults
            .transpose()
            .mapIndexed { index, list ->
                val i = list.map { "%.2f".format(it) }
                TableFinalData(
                    name = "x ${index + 1}",
                    pessimistic = i.getOrNull(0) ?: "",
                    cautious = i.getOrNull(1) ?: "",
                    average = i.getOrNull(2) ?: "",
                    optimistic = i.getOrNull(3) ?: "",
                )
            } +
                TableFinalData(
                    name = StringsData.labelTableFinalCol5,
                    pessimistic = "x${best[0] + 1}",
                    cautious = "x${best[1] + 1}",
                    average = "x${best[2] + 1}",
                    optimistic = "x${best[0] + 1}"
                ),
        headerTableTitles = with(StringsData) {
            listOf(
                " ",
                labelTableFinalCol1,
                labelTableFinalCol2,
                labelTableFinalCol3,
                labelTableFinalCol4
            )
        },
        enableTableHeaderTitles = true,
        tableElevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        rowBorderColor = MaterialTheme.colorScheme.outlineVariant,
        rowTextStyle = MaterialTheme.typography.bodyMedium,
        headerTitlesBackGroundColor = MaterialTheme.colorScheme.tertiaryContainer,
        tableRowColors = with(MaterialTheme.colorScheme) { listOf(surface, background) },
        headerTitlesTextStyle = MaterialTheme.typography.titleLarge,
        headerTitlesBorderColor = MaterialTheme.colorScheme.onTertiaryContainer,
        textAlign = TextAlign.Center,
    )
}

data class TableFinalData(
    val name: String,
    val pessimistic: String,
    val cautious: String,
    val average: String,
    val optimistic: String,
)

@Composable
private fun TableWeightNormalize(values: List<Double>) {

    Text(
        text = StringsData.labelTable2,
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500,
    )

    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.shapes.extraLarge
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TableComponent(
            tableColumns = null,
            items = values.mapIndexed { i, it -> listOf(i.toString(), "%.2f".format(it)) },
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            bodyContent = { columnIndex: Int, item: String ->
                when (columnIndex) {
                    0 -> LabelWithIndexes(
                        label = "a",
                        indexTop = "",
                        indexBottom = item,
                        modifier = Modifier
                            .weight(.1f)
                            .padding(start = 16.dp),
                        fontStyle = FontStyle.Italic,
                        color = MaterialTheme.colorScheme.tertiary,
                    )


                    1 -> Text(
                        text = item,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(.1f),
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        )
    }
}


fun <A, B, C> List<Triple<A, B, C>>.unzip3(): Triple<List<A>, List<B>, List<C>> {
    val listA = mutableListOf<A>()
    val listB = mutableListOf<B>()
    val listC = mutableListOf<C>()

    for ((a, b, c) in this) {
        listA.add(a)
        listB.add(b)
        listC.add(c)
    }

    return Triple(listA, listB, listC)
}