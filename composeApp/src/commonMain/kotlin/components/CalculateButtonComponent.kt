package components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import third.StringsData

@Composable
fun CalculateButtonComponent(stateCalcPressed: MutableState<Boolean>) {
    val makeSpacer: @Composable () -> Unit =
        { Spacer(modifier = Modifier.height(16.dp).width(16.dp)) }

    makeSpacer()
    FilledTonalButton(
        onClick = { stateCalcPressed.value = true },
        enabled = !stateCalcPressed.value
    ) {
        Text(text = StringsData.labelCalculate)
    }
    makeSpacer()
}