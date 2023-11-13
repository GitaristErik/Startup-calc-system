package components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun InputNumberComponent(
    value: Int,
    onChange: (Int) -> Unit,
    charLimit: Int = 3,
    label: String? = null,
    modifier: Modifier = Modifier
) {
//    var errorState by rememberSaveable { mutableStateOf<String?>(null) }

    val validate = { text: String ->
        text.length <= charLimit &&
                text.isNotEmpty() &&
                (text.toIntOrNull() != null)// || text.toDoubleOrNull() != null)
//                text.toInt() in 0..255
    }

    TextField(
        modifier = modifier,
        value = value.toString(),
        onValueChange = {
            if (validate(it)) {
                onChange(it.toInt())
            }
        },
        label = { Text(label ?: "") },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        keyboardActions = KeyboardActions {  },
        maxLines = 1,
        singleLine = true,
//        trailingIcon = {
//            if (errorState != null)
//                Icon(Icons.Filled.Error, errorState, tint = MaterialTheme.colorScheme.error)
//        }
    )
}