package org.example.project.screens

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.CalculateButtonComponent
import org.example.project.components.ListInputsAndMenuComponent
import org.example.project.utils.advancedShadow
import third.CalculatedNeuroComponent
import third.DePhasingResultComponent
import third.StringsData
import third.TablePhasingComponent
import utils.ThirdAlgorithm

@Composable
fun ColumnScope.ThirdScreen() {

    val stateCalcPressed = remember { mutableStateOf(false) }

    val stateInputData: Map<String, List<MutableState<Triple<ThirdAlgorithm.L?, Double?, Int>>>> =
        StringsData.defaultData.map { (key, list) ->
            key to list.map {
                remember { mutableStateOf(it) }.also {
                    LaunchedEffect(it.value) { stateCalcPressed.value = false }
                }
            }
        }.toMap()

    ListInputsAndMenuComponent(stateInputData, Modifier.padding(horizontal = 8.dp))

    CalculateButtonComponent(stateCalcPressed)

    if (!stateCalcPressed.value) return

    val makeSpacer2: @Composable () -> Unit = {
        Spacer(
            modifier = Modifier
                .height(32.dp)
                .width(32.dp)
        )
    }
    val calculator = ThirdAlgorithm()


    val itemsX = StringsData.defaultData.flatMap { (_, v) ->
        v.mapNotNull { it.second?.toString() }
    }
    TablePhasingComponent(itemsX)

    makeSpacer2()

    val calculatedValue = 0.098765
    CalculatedNeuroComponent(calculatedValue)

    makeSpacer2()

    DePhasingResultComponent(
        teamRate = calculator.teamRate,
        setShadow = {
            advancedShadow(
                color = calculator.teamRate.textColor,
                alpha = .1f,
                elevation = 20.dp,
                cornersRadius = 16.dp,
                shadowBlurRadius = 20.dp,
                offsetY = (-10).dp,
                offsetX = (-10).dp
            )
        }
    )

    makeSpacer2()

}