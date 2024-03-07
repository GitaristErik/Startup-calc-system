package org.example.project.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.InputNumberComponent
import utils.Criteria

@Composable
fun CriteriaComponent(
    modifier: Modifier = Modifier,
    prefix: @Composable (() -> Unit)? = null,
    cornerShape: RoundedCornerShape = RoundedCornerShape(0.dp),
    isOutlined: Boolean = false,
    value: Criteria,
    hint: String? = null,
    onChanges: (Criteria) -> Unit,
) {
    when {

        value is Criteria.NFC -> DropdownMenuComponent(
            value, cornerShape, isOutlined, prefix, onSelect = onChanges, modifier = modifier,
            hint = hint
        )

        value is Criteria.OS -> DropdownMenuComponent(
            value, cornerShape, isOutlined, prefix, onSelect = onChanges,
            modifier = modifier
                .width(200.dp)
                .fillMaxWidth(),
            hint = hint
        )

        value is Criteria.Processor -> DropdownMenuComponent(
            value, cornerShape, isOutlined, prefix, onSelect = onChanges, modifier = modifier,
            hint = hint
        )


        value.digitalValue is Double -> InputNumberComponent(
            value = value.digitalValue.toDouble(),
            onChange = {
                onChanges(
                    value::class.java.getDeclaredConstructor(Double::class.java).newInstance(it)
                )
            }, outlined = isOutlined, shape = cornerShape, prefix = prefix, modifier = modifier,
            label = hint
        )

        value.digitalValue is Int -> InputNumberComponent(
            value = value.digitalValue.toInt(),
            onChange = {
                onChanges(
                    value::class.java.getDeclaredConstructor(Int::class.java).newInstance(it)
                )
            }, outlined = isOutlined, shape = cornerShape, prefix = prefix, modifier = modifier,
            label = hint
        )
    }
}