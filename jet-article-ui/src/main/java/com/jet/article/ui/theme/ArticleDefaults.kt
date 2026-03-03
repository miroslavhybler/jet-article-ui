package com.jet.article.ui.theme

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.jet.article.core.ArticleElement
import com.jet.article.ui.LinkClickHandler
import com.jet.article.ui.elements.Code
import com.jet.article.ui.elements.ContentList
import com.jet.article.ui.elements.ContentTable
import com.jet.article.ui.elements.ImageElement
import com.jet.article.ui.elements.QuoteContainer
import com.jet.article.ui.elements.TextElement


val LocalArticleDimensions: ProvidableCompositionLocal<ArticleDimensions> =
    compositionLocalOf(
        defaultFactory = ArticleDimensions::Default,
    )

val LocalArticleUrl: ProvidableCompositionLocal<String?> = compositionLocalOf(
    defaultFactory = { null },
)

val LocalLinkClickHandler: ProvidableCompositionLocal<LinkClickHandler?> = compositionLocalOf(
    defaultFactory = { null },
)

/**
 * Default composables for the containers UI.
 *
 * @author Miroslav Hýbler
 * created on 15.02.2026
 */
object ArticleDefaults {

    val DefaultContentPadding: PaddingValues = PaddingValues(
        top = ArticleDimensions.Default.topLinePadding,
        bottom = ArticleDimensions.Default.bottomLinePadding,
        start = ArticleDimensions.Default.defaultHorizontalPadding,
        end = ArticleDimensions.Default.defaultHorizontalPadding,
    )


    /**
     * The default composable for a [ArticleElement.Text].
     */
    @Composable
    fun Text(text: ArticleElement.Text) {
        TextElement(text = text)
    }

    /**
     * The default composable for a [ArticleElement.Image].
     */
    @Composable
    fun Image(image: ArticleElement.Image) {
        ImageElement(image = image)
    }

    /**
     * The default composable for a [ArticleElement.Quote].
     */
    @Composable
    fun Quote(quote: ArticleElement.Quote) {
        QuoteContainer(quote = quote)
    }

    /**
     * The default composable for a [ArticleElement.ContentTable].
     */
    @Composable
    fun Table(table: ArticleElement.ContentTable) {
        ContentTable(table = table)
    }

    /**
     * The default composable for a [ArticleElement.ContentList].
     */
    @Composable
    fun List(
        list: ArticleElement.ContentList,
        textContent: @Composable (text: ArticleElement.Text) -> Unit = { Text(it) },
    ) {
        ContentList(list = list, textContent = textContent)
    }

    /**
     * The default composable for a code block.
     */
    @Composable
    fun Code(code: ArticleElement.Text) {
        Code(text = code)
    }
}
