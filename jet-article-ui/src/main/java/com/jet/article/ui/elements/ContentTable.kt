package com.jet.article.ui.elements

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.LocalArticleDimensions

@Composable
private fun TableCellContent(
    cell: ArticleElement.ContentTable.TableCell,
    isHeader: Boolean,
    headerColor: Color,
    borderColor: Color
) {
    val dimensions = LocalArticleDimensions.current

    Column(
        modifier = Modifier
            .border(width = 0.5.dp, color = borderColor)
            .padding(paddingValues = dimensions.tableContentPadding)
    ) {
        cell.content.forEach { element ->
            if (element is ArticleElement.Text) {
                Text(
                    text = element.text,
                    textAlign = TextAlign.Center,
                    color = if (isHeader) headerColor else MaterialTheme.colorScheme.onBackground
                )
            }
        }
    }
}

@Composable
fun ContentTable(
    table: ArticleElement.ContentTable,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.medium,
    headerColor: Color = MaterialTheme.colorScheme.primary,
    borderColor: Color = MaterialTheme.colorScheme.outline,
) {
    val scrollState = rememberScrollState()
    val dimensions = LocalArticleDimensions.current
    val minColumnWidth = dimensions.tableMinColumnWidth
    val maxColumnWidth = dimensions.tableMaxColumnWidth

    SubcomposeLayout(
        modifier = modifier
            .horizontalScroll(state = scrollState)
            .padding(
                start = dimensions.startPadding,
                end = dimensions.endPadding,
            )
            .border(width = 1.dp, color = borderColor, shape = shape)
            .clip(shape = shape)
    ) { constraints ->
        val columnCount = table.rows.firstOrNull()?.cells?.size ?: 0
        if (columnCount == 0) {
            return@SubcomposeLayout layout(width = 0, height = 0) {}
        }

        // 1. Measurement pass to determine column widths
        val columnWidths = MutableList(size = columnCount) { minColumnWidth.toPx() }
        val measurementPlaceables = subcompose(slotId = "measurement") {
            table.rows.forEach { row ->
                val isHeader = table.rows.firstOrNull() == row
                row.cells.forEach { cell ->
                    TableCellContent(
                        cell = cell,
                        isHeader = isHeader,
                        headerColor = headerColor,
                        borderColor = borderColor
                    )
                }
            }
        }.map {
            // Measure with unconstrained width to find the preferred width
            it.measure(Constraints())
        }

        measurementPlaceables.chunked(size = columnCount).forEach { rowOfPlaceables ->
            rowOfPlaceables.forEachIndexed { index, placeable ->
                if (index < columnWidths.size) {
                    columnWidths[index] = maxOf(
                        a = columnWidths[index],
                        b = placeable.width.toFloat()
                    )
                }
            }
        }

        // Constrain column widths
        columnWidths.forEachIndexed { index, width ->
            columnWidths[index] = width.coerceIn(
                minimumValue = minColumnWidth.toPx(),
                maximumValue = maxColumnWidth.toPx()
            )
        }
        val totalWidth = columnWidths.sum().toInt()

        // 2. Layout pass
        val layoutPlaceables = subcompose(slotId = "layout") {
            table.rows.forEach { row ->
                val isHeader = table.rows.firstOrNull() == row
                row.cells.forEach { cell ->
                    TableCellContent(
                        cell = cell,
                        isHeader = isHeader,
                        headerColor = headerColor,
                        borderColor = borderColor
                    )
                }
            }
        }.mapIndexed { index, measurable ->
            val columnIndex = index % columnCount
            measurable.measure(constraints = Constraints.fixedWidth(width = columnWidths[columnIndex].toInt()))
        }

        val totalHeight = layoutPlaceables.chunked(size = columnCount)
            .sumOf(selector = { rowOfPlaceables ->
                rowOfPlaceables.maxOfOrNull(selector = { it.height }) ?: 0
            })

        layout(width = totalWidth, height = totalHeight) {
            var y = 0
            layoutPlaceables.chunked(size = columnCount).forEach { rowOfPlaceables ->
                var x = 0
                val rowHeight = rowOfPlaceables.maxOfOrNull { it.height } ?: 0
                rowOfPlaceables.forEachIndexed { index, placeable ->
                    if (index < columnWidths.size) {
                        placeable.placeRelative(x = x, y = y)
                        x += columnWidths[index].toInt()
                    }
                }
                y += rowHeight
            }
        }
    }
}
