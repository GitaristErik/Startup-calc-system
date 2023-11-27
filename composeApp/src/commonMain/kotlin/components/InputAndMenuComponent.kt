package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import utils.SecondAlgorithm

@Composable
fun InputAndMenuComponent(
    state: MutableState<Pair<SecondAlgorithm.L, Double>>,
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit)? = null,
    horizontalSpace: Dp = 4.dp,
    isOutlinedMenu: Boolean = false,
) {
    Row(
        modifier = modifier.fillMaxHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(horizontalSpace)
    ) {

        val cornerRound = 16.dp

        InputNumberComponent<Double>(
            value = state.value.second,
            onChange = { state.value = state.value.first to it },
            charLimit = 10,
            modifier = Modifier
                .weight(.2f)
                .fillMaxHeight(),
            outlined = true,
            shape = RoundedCornerShape(topStart = cornerRound, bottomStart = cornerRound),
            prefix = prefix,
        )

        DropdownMenuLComponent(
            state.value.first,
            shape = RoundedCornerShape(topEnd = cornerRound, bottomEnd = cornerRound),
            isOutlinedMenu = isOutlinedMenu,
            modifier = Modifier
                .weight(.2f)
//                .wrapContentWidth()
//                .widthIn(min = 128.dp, max = 196.dp)
        ) { state.value = it to state.value.second }
    }
}