package components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun <T : Number> InputNumberComponent(
    value: T,
    onChange: (T) -> Unit,
    charLimit: Int = 3,
    label: String? = null,
    modifier: Modifier = Modifier,
    outlined: Boolean = false,
    shape: Shape = RoundedCornerShape(8.dp),
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    prefix: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
) {
//    var errorState by rememberSaveable { mutableStateOf<String?>(null) }


    val parseValue = { text: String ->
        when (value) {
            is Int -> text.toIntOrNull()
            is Long -> text.toLongOrNull()
            is Short -> text.toShortOrNull()
            is Byte -> text.toByteOrNull()
            is Double -> text.toDoubleOrNull()
            is Float -> text.toFloatOrNull()
            else -> null
        } as? T
    }

    val validate = { text: String ->
        text.length <= charLimit && text.isNotEmpty() && parseValue(text) != null
//                text.toInt() in 0..255
    }


    if (outlined) {
        OutlinedTextField(
            modifier = modifier,
            value = value.toString(),
            onValueChange = {
                if (validate(it)) {
                    onChange(parseValue(it)!!)
                }
            },
            label = { Text(label ?: "") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions { },
            maxLines = 1,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            singleLine = true,
            shape = shape
//        trailingIcon = {
//            if (errorState != null)
//                Icon(Icons.Filled.Error, errorState, tint = MaterialTheme.colorScheme.error)
//        }
        )
    } else {
        TextField(
            modifier = modifier,
            value = value.toString(),
            onValueChange = {
                if (validate(it)) {
                    onChange(parseValue(it)!!)
                }
            },
            label = { Text(label ?: "") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            keyboardActions = KeyboardActions { },
            maxLines = 1,
            placeholder = placeholder,
            leadingIcon = leadingIcon,
            trailingIcon = trailingIcon,
            prefix = prefix,
            suffix = suffix,
            singleLine = true,
            shape = shape,
//        trailingIcon = {
//            if (errorState != null)
//                Icon(Icons.Filled.Error, errorState, tint = MaterialTheme.colorScheme.error)
//        }
        )
    }
}