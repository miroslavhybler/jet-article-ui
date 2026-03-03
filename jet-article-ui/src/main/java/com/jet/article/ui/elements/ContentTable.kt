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
    Column(
        modifier = Modifier
            .border(0.5.dp, borderColor)
            .padding(8.dp)
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
            .clip(shape)
            .horizontalScroll(scrollState)
            .border(1.dp, borderColor, shape)
    ) { constraints ->
        val columnCount = table.rows.firstOrNull()?.cells?.size ?: 0
        if (columnCount == 0) {
            return@SubcomposeLayout layout(0, 0) {}
        }

        // 1. Measurement pass to determine column widths
        val columnWidths = MutableList(columnCount) { minColumnWidth.toPx() }
        val measurementPlaceables = subcompose("measurement") {
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

        measurementPlaceables.chunked(columnCount).forEach { rowOfPlaceables ->
            rowOfPlaceables.forEachIndexed { index, placeable ->
                if (index < columnWidths.size) {
                    columnWidths[index] = maxOf(columnWidths[index], placeable.width.toFloat())
                }
            }
        }

        // Constrain column widths
        columnWidths.forEachIndexed { index, width ->
            columnWidths[index] = width.coerceIn(
                minColumnWidth.toPx(),
                maxColumnWidth.toPx()
            )
        }
        val totalWidth = columnWidths.sum().toInt()

        // 2. Layout pass
        val layoutPlaceables = subcompose("layout") {
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
            measurable.measure(Constraints.fixedWidth(columnWidths[columnIndex].toInt()))
        }

        val totalHeight = layoutPlaceables.chunked(columnCount)
            .sumOf { rowOfPlaceables ->
                rowOfPlaceables.maxOfOrNull { it.height } ?: 0
            }

        layout(totalWidth, totalHeight) {
            var y = 0
            layoutPlaceables.chunked(columnCount).forEach { rowOfPlaceables ->
                var x = 0
                val rowHeight = rowOfPlaceables.maxOfOrNull { it.height } ?: 0
                rowOfPlaceables.forEachIndexed { index, placeable ->
                    if (index < columnWidths.size) {
                        placeable.placeRelative(x, y)
                        x += columnWidths[index].toInt()
                    }
                }
                y += rowHeight
            }
        }
    }
}
