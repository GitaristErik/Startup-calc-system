package third

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import components.LabelWithIndexes
import components.TableColumn
import components.TableComponent
import utils.ThirdAlgorithm

@Composable
fun ColumnScope.TablePhasingComponent(valuesX: List<Double>) {

    Text(
        text = StringsData.labelTable1,
        modifier = Modifier.padding(4.dp),
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        fontWeight = FontWeight.W500,
//        fontStyle = FontStyle.Italic
    )

    Column(
        Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .border(
                2.dp,
                MaterialTheme.colorScheme.secondaryContainer,
                MaterialTheme.shapes.extraLarge
            ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        TableComponent(
            tableColumns = listOf(
                TableColumn(
                    StringsData.labelTable1Col1,
                    .1f,
                    TextAlign.Left,
                    fontStyle = FontStyle.Italic
                ),
                TableColumn(
                    StringsData.labelTable1Col2,
                    .1f,
                    TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary,
                ),
            ),
            items = ThirdAlgorithm.indexedKeysK.mapIndexed { i, it ->
                listOf(it, "%.3f".format(valuesX[i])) // zip keys with values X
            },
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp),
            bodyContent = { columnIndex: Int, item: String ->
                when (columnIndex) {
                    0 -> item.split("|").let { (key, index) ->
                        LabelWithIndexes(
                            label = "K",
                            indexTop = key,
                            indexBottom = index,
                            modifier = Modifier.weight(.1f).padding(start = 16.dp),
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.tertiary,
                        )
                    }

                    1 -> Text(
                        text = item,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(.1f),
                        color = MaterialTheme.colorScheme.primary,
                        fontStyle = FontStyle.Normal,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        )
    }
}
