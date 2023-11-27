package screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import components.CalculateButtonComponent
import components.ListInputsAndMenuComponent
import third.CalculatedNeuroComponent
import third.DePhasingResultComponent
import third.StringsData
import third.TablePhasingComponent
import utils.ThirdAlgorithm

@Composable
fun ThirdScreen() {

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


    val makeSpacer2: @Composable () -> Unit =
        { Spacer(modifier = Modifier.height(32.dp).width(32.dp)) }

    val calculator = ThirdAlgorithm(
        stateInputData.mapValues { (_, v) -> v.map { it.value } }
    )


    Row {
        Column(
            modifier = Modifier.weight(.5f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TablePhasingComponent(calculator.calculatedMembershipFlatten)
        }


        Column(
            modifier = Modifier.weight(.5f),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {

            CalculatedNeuroComponent(calculator.defuzzification)

            makeSpacer2()

            DePhasingResultComponent(
                teamRate = calculator.teamRate,
                setShadow = {
                    shadow(
                        elevation = 5.dp,
                        shape = MaterialTheme.shapes.extraLarge,
                        ambientColor = MaterialTheme.colorScheme.tertiaryContainer,
                        spotColor = MaterialTheme.colorScheme.onTertiaryContainer
                    )
                }
            )
        }
    }
    makeSpacer2()
}