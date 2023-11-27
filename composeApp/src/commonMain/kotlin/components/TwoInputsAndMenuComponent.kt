package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import components.DropdownMenuLComponent
import components.InputNumberComponent
import utils.ThirdAlgorithm

@Composable
fun TwoInputsAndMenuComponent(
    state: MutableState<Triple<ThirdAlgorithm.L?, Double?, Int>>,
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit)? = null,
    horizontalSpace: Dp = 4.dp,
    isOutlinedMenu: Boolean = false,
) {
    if(state.value.first == null || state.value.second == null) return

    Row(
        modifier = modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
    ) {

        val cornerRound = 16.dp

        InputNumberComponent<Double>(
            value = state.value.second!!,
            onChange = { state.value = with(state.value) { Triple(first, it, third) } },
            charLimit = 10,
            modifier = Modifier
                .weight(.3f)
                .fillMaxHeight(),
            outlined = true,
            shape = RoundedCornerShape(topStart = cornerRound, bottomStart = cornerRound),
            prefix = prefix,
        )

        InputNumberComponent<Int>(
            value = state.value.third,
            onChange = { state.value = with(state.value) { Triple(first, second, it) } },
            charLimit = 10,
            modifier = Modifier
                .weight(.2f)
                .fillMaxHeight(),
            outlined = true,
//            shape = RoundedCornerShape(topStart = cornerRound, bottomStart = cornerRound),
//            prefix = prefix,
        )

        DropdownMenuLComponent(
            state.value.first!!,
            shape = RoundedCornerShape(topEnd = cornerRound, bottomEnd = cornerRound),
            isOutlinedMenu = isOutlinedMenu,
            modifier = Modifier
                .weight(.5f)
//                .wrapContentWidth()
//                .widthIn(min = 128.dp, max = 196.dp)
        )
        { state.value = with(state.value) { Triple(it, second, third) } }
    }
}