package com.jet.article.ui.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * @author Miroslav Hýbler <br>
 * created on 12.02.2026
 * @since 1.0.0
 */
data class ArticleDimensions constructor(
    val defaultHorizontalPadding: Dp,
    val topLinePadding: Dp,
    val bottomLinePadding: Dp,
    val quoteVerticalPadding: Dp,
    val quoteLineWidth: Dp,
    val titleTopLinePadding: Dp,
    val tableMinColumnWidth: Dp,
    val tableMaxColumnWidth: Dp,
) {
    companion object {
        val Default: ArticleDimensions = ArticleDimensions(
            defaultHorizontalPadding = 16.dp,
            topLinePadding = 24.dp,
            bottomLinePadding = 32.dp,
            quoteVerticalPadding = 8.dp,
            quoteLineWidth = 5.dp,
            titleTopLinePadding = 24.dp,
            tableMinColumnWidth = 128.dp,
            tableMaxColumnWidth = 256.dp,
        )
    }
}
