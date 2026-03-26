package com.jet.article.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jet.article.core.ArticleElement
import com.jet.article.ui.LinkClickHandler
import com.jet.article.ui.theme.ArticleColorScheme
import com.jet.article.ui.theme.ArticleDefaults
import com.jet.article.ui.theme.ArticleDimensions
import com.jet.article.ui.theme.ArticleTypography
import com.jet.article.ui.theme.articleColorScheme
import com.jet.article.ui.theme.articleTypography

/**
 * A Column that displays a list of [ArticleElement]s.
 *
 * @param state The state of the article, containing the list of elements.
 * @param modifier The modifier to be applied to the Column.
 * @param contentPadding The padding to be applied to the content of the Column.
 * @param verticalArrangement The vertical arrangement of the Column's children.
 * @param horizontalAlignment The horizontal alignment of the Column's children.
 * @param linkCallback The callback to be invoked when a link is clicked.
 * @param header A composable to be displayed at the top of the Column.
 * @param footer A composable to be displayed at the bottom of the Column.
 * @param text A composable to display a [ArticleElement.Text].
 * @param image A composable to display a [ArticleElement.Image].
 * @param quote A composable to display a [ArticleElement.Quote].
 * @param table A composable to display a [ArticleElement.ContentTable].
 * @param list A composable to display a [ArticleElement.ContentList].
 * @param code A composable to display a [ArticleElement.Text] that is a code block.
 *
 * @author Miroslav Hýbler <br>
 * created on 15.02.2026
 */
@Composable
fun ArticleColumn(
    state: ArticleState,
    modifier: Modifier = Modifier,
    colorScheme: ArticleColorScheme = articleColorScheme(),
    typography: ArticleTypography = articleTypography(),
    dimensions: ArticleDimensions = ArticleDimensions.Default,
    contentPadding: PaddingValues = ArticleDefaults.DefaultContentPadding,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    linkCallback: LinkClickHandler.LinkCallback = LinkClickHandler.LinkCallback(),
    header: @Composable (() -> Unit)? = null,
    footer: @Composable (() -> Unit)? = null,
    text: @Composable (ArticleElement.Text) -> Unit = { ArticleDefaults.Text(text = it) },
    image: @Composable (ArticleElement.Image) -> Unit = { ArticleDefaults.Image(image = it) },
    quote: @Composable (ArticleElement.Quote) -> Unit = { ArticleDefaults.Quote(quote = it) },
    table: @Composable (ArticleElement.ContentTable) -> Unit = { ArticleDefaults.Table(table = it) },
    list: @Composable (ArticleElement.ContentList) -> Unit = { ArticleDefaults.List(list = it) },
    code: @Composable (ArticleElement.Text) -> Unit = { ArticleDefaults.Code(code = it) }
) {
    val articleUrl by remember { derivedStateOf { state.data.url } }
    val linkClickHandler = remember { LinkClickHandler() }

    LaunchedEffect(key1 = linkCallback) {
        linkClickHandler.callback = linkCallback
    }


    ArticleContainer(
        colorScheme = colorScheme,
        typography = typography,
        dimensions = dimensions,
        articleUrl = articleUrl,
        linkClickHandler = linkClickHandler,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues = contentPadding),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment,
        ) {
            if (header != null) {
                header()
            }

            state.data.elements.forEach { element ->
                when (element) {
                    is ArticleElement.Text -> {
                        if (element.isCode) {
                            code(element)
                        } else {
                            text(element)
                        }
                    }

                    is ArticleElement.Image -> image(element)
                    is ArticleElement.Quote -> quote(element)
                    is ArticleElement.ContentTable -> table(element)
                    is ArticleElement.ContentList -> list(element)
                }
            }

            if (footer != null) {
                footer()
            }
        }
    }
}
