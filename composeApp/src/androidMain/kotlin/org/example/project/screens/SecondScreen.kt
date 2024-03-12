package org.example.project.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.breens.beetablescompose.BeeTablesCompose
import components.InputNumberComponent
import components.LabelWithIndexes
import components.TableComponent
import org.example.project.components.BodyComponent
import org.example.project.components.ElectreResultComponent
import org.example.project.components.TitleComponent2
import org.example.project.utils.advancedShadow
import second.StringsData
import utils.AlgorithmFirst
import utils.Convolution.AverageConvolution.transpose
import utils.Criteria
import utils.Electre
import utils.Electre.ElectreDirection
import utils.ThirdAlgorithm
import java.io.Serializable

data class Quad<out A, out B, out C, out D>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D
) : Serializable {
    override fun toString(): String = "($first, $second, $third, $fourth)"
}

@Composable
fun SecondScreen() {

    var stateCalcPressed by remember { mutableStateOf(false) }

    //indifference_threshold
    var stateInputDataIT by remember { mutableDoubleStateOf(0.6) }
    LaunchedEffect(stateInputDataIT) { stateCalcPressed = false }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(0.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = StringsData.labelIndifferenceThreshold,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.outline,
            textAlign = TextAlign.Left,
            modifier = Modifier.weight(.75f)
        )
        InputNumberComponent(
            value = stateInputDataIT,
            onChange = { stateInputDataIT = it },
            modifier = Modifier.weight(.25f),
        )
    }


    val (
        stateInputDataA,
        stateInputDataT,
        stateInputDataListK,
        stateInputDataV) = listOf(
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
        Quad(
            remember { mutableIntStateOf(a) },
            remember(list[0].localizedName) { mutableDoubleStateOf(t) },
            remember(list.hashCode()) { mutableStateOf(list) },
            remember(list[0].localizedName) { mutableDoubleStateOf(t) },
        )
    }.onEachIndexed { index, (a, t, k, v) ->
        LaunchedEffect(t.doubleValue) { stateCalcPressed = false }
        LaunchedEffect(a.intValue) { stateCalcPressed = false }
        TitleComponent2(
            index = index,
            state = t,
            localizedName = k.value[0].localizedName,
            stateWeight = a,
            stateVeto = v
        )

        LaunchedEffect(k.value) { stateCalcPressed = false }
        BodyComponent(k)
    }.unzip()



    Spacer(modifier = Modifier.height(16.dp))
    FilledTonalButton(
        onClick = { stateCalcPressed = true },
        enabled = !stateCalcPressed
    ) {
        Text(text = first.StringsData.labelCalculate)
    }

    if (!stateCalcPressed) return

    Spacer(modifier = Modifier.height(16.dp))

    val vetoes = stateInputDataV.map { it.doubleValue }

    val calculator = AlgorithmFirst(
        criteria = stateInputDataListK.map { it.value },
        weights = stateInputDataA.map { it.intValue.toDouble() },
        T = stateInputDataT.map { it.doubleValue },
    )

    Text(
        modifier = Modifier.padding(16.dp),
        text = first.StringsData.labelTable1,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )


    MadeTable(
        data = calculator.normalizedCriteria.mapIndexed { index, list ->
            val i = list.map { "%.2f".format(it) }
            TableNormalizeData2(
                index = "K ${index + 1}",
                x1 = i.getOrNull(0) ?: "",
                x2 = i.getOrNull(1) ?: "",
                x3 = i.getOrNull(2) ?: "",
                x4 = i.getOrNull(3) ?: ""
            )
        },
        titles = listOf(" ") + List(4) { "x${it + 1}" },
    )


    Text(
        text = first.StringsData.labelTable2,
        modifier = Modifier.padding(16.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
    )

    MadeTable(
        data = calculator.normalizedWeights
            .zip(vetoes)
            .mapIndexed { index, (w, v) ->
                Triple(
                    "${index + 1}",
                    "%.2f".format(w),
                    "%.2f".format(v)
                )
            },
        titles = listOf(" ", "weight", "veto")
    )

    val prefs = List(11) { if (it == 0 || it == 10) ElectreDirection.MIN else ElectreDirection.MAX }

    val result1 = Electre(criteria = calculator.normalizedCriteria.transpose())
        .solve(
            weights = calculator.normalizedWeights,
            vetoes = vetoes,
            concordanceThreshold = stateInputDataIT,
            prefs = prefs
        )


    val shadow: @Composable (Modifier.() -> Modifier) = {
        advancedShadow(
            color = ThirdAlgorithm.Y_LINGUISTIC.MEDIUM_HIGH.textColor,
            alpha = .1f,
            elevation = 20.dp,
            cornersRadius = 16.dp,
            shadowBlurRadius = 20.dp,
            offsetY = (-10).dp,
            offsetX = (-10).dp
        )
    }

    ElectreResultComponent(
        kernels = result1.first.map { "x$it" },
        data = result1.second.map { "x$it" },
        setShadow = shadow
    )

    Text(
        modifier = Modifier.padding(top = 32.dp, bottom = 32.dp),
        text = first.StringsData.labelMethod2,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    Text(
        modifier = Modifier.padding(top = 16.dp, bottom = 32.dp),
        text = first.StringsData.labelTable3,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center
    )

    MadeTable(
        data = calculator.matrixZ.mapIndexed { index, list ->
            val i = list.map { "%.2f".format(it) }
            TableNormalizeData2(
                index = "K ${index + 1}",
                x1 = i.getOrNull(0) ?: "",
                x2 = i.getOrNull(1) ?: "",
                x3 = i.getOrNull(2) ?: "",
                x4 = i.getOrNull(3) ?: ""
            )
        },
        titles = listOf(" ") + List(4) { "x${it + 1}" },
    )


    val result2 = Electre(criteria = calculator.normalizedCriteria.transpose())
        .solve(
            weights = calculator.normalizedWeights,
            vetoes = vetoes,
            concordanceThreshold = stateInputDataIT,
            prefs = prefs
        )


    ElectreResultComponent(
        kernels = result2.first.map { "x$it" },
        data = result2.second.map { "x$it" },
        setShadow = shadow
    )

    Spacer(modifier = Modifier.height(56.dp))
}

@Composable
inline fun <reified T : Any> MadeTable(data: List<T>, titles: List<String>) {
    BeeTablesCompose(
        data = data,
        headerTableTitles = titles,
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

data class TableNormalizeData2(
    val index: String,
    val x1: String,
    val x2: String,
    val x3: String,
    val x4: String,
)


private fun <A, B, C, D> List<Quad<A, B, C, D>>.unzip(): Quad<List<A>, List<B>, List<C>, List<D>> {
    val listA = mutableListOf<A>()
    val listB = mutableListOf<B>()
    val listC = mutableListOf<C>()
    val listD = mutableListOf<D>()

    for ((a, b, c, d) in this) {
        listA.add(a)
        listB.add(b)
        listC.add(c)
        listD.add(d)
    }

    return Quad(listA, listB, listC, listD)
}