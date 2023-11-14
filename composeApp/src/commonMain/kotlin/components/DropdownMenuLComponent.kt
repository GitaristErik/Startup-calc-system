package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import second.StringsData
import utils.SecondAlgorithm

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuLComponent(
    selectedItem: SecondAlgorithm.L,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.extraSmall,
    isOutlinedMenu: Boolean = false,
    onSelect: (SecondAlgorithm.L) -> Unit = {},
) {

    var expanded by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    // menu box
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = modifier.padding(top = if (!isOutlinedMenu) 8.dp else 0.dp),
        //.wrapContentWidth(Alignment.CenterHorizontally),
        onExpandedChange = { expanded = !expanded },
    ) {

        if (!isOutlinedMenu) {
            TextField(
                modifier = Modifier.menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
                readOnly = true,
                value = selectedItem.title,
                onValueChange = {},
                shape = shape,
                label = {
                    Text(
                        text = StringsData.labelRiskLevel,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                leadingIcon = {
                    selectedItem.imageIcon?.let { image ->
                        Icon(image, contentDescription = null)
                    }
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
                ),
            )
        } else {
            OutlinedTextField(
                modifier = Modifier.menuAnchor(), // menuAnchor modifier must be passed to the text field for correctness.
                readOnly = true,
                value = selectedItem.title,
                onValueChange = {},
                shape = shape,
                label = {
                    Text(
                        text = StringsData.labelRiskLevel,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                    )
                },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                leadingIcon = {
                    selectedItem.imageIcon?.let { image ->
                        Icon(image, contentDescription = null)
                    }
                },
                colors = ExposedDropdownMenuDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
//                cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
//                focusedContainerColor = Color.White,
//                unfocusedContainerColor = Color.White,
                ),
            )
        }

        // menu
        ExposedDropdownMenu(
            expanded = expanded,
            modifier = Modifier.widthIn(min = 320.dp, max = 720.dp),
//                .animateContentSize()
            onDismissRequest = { expanded = false },
        ) {

            Text(
                text = StringsData.labelRiskLevel,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                color = MaterialTheme.colorScheme.tertiary,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .menuAnchor()
            )


            SecondAlgorithm.L.entries.forEach { l ->
                DropdownMenuItem(
                    text = { Text("${l.title}: ${l.description}") },
                    onClick = {
                        onSelect(l)
                        expanded = false
                    },
                    leadingIcon = {
                        l.imageIcon?.let { image -> Icon(image, contentDescription = null) }
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }

    LaunchedEffect(expanded) {
        if (expanded) {
            // scroll to the last item
            scrollState.scrollTo(scrollState.maxValue)
        }
    }
}