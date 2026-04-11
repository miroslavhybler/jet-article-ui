package com.jet.article.ui.elements

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.ArticleTheme
import com.jet.article.ui.theme.LocalArticleColorScheme
import com.jet.article.ui.theme.LocalArticleDimensions
import com.jet.article.ui.theme.LocalArticleTypography
import com.jet.article.ui.theme.LocalLinkClickHandler
import com.jet.article.ui.theme.articleColorScheme
import com.jet.article.ui.theme.articleTypography
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextElement(
    text: ArticleElement.Text,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalArticleDimensions.current
    val linkHandler = LocalLinkClickHandler.current
    val colorSheme = LocalArticleColorScheme.current

    val textStyle = when (text.tag) {
        "h1" -> ArticleTheme.typography.h1
        "h2" -> ArticleTheme.typography.h2
        "h3" -> ArticleTheme.typography.h3
        "h4" -> ArticleTheme.typography.h4
        "h5" -> ArticleTheme.typography.h5
        "h6" -> ArticleTheme.typography.h6
        else -> ArticleTheme.typography.text
    }
    val color = if (text.isTitle)
        ArticleTheme.colorScheme.titleColor
    else
        ArticleTheme.colorScheme.textColor

    val finalFormattedText = remember(key1 = text.text) {
        text.text.mapAnnotations { range ->
            val annotation = range.item
            val updatedAnnotation = if (annotation is LinkAnnotation.Clickable) {
                annotation.copy(
                    styles = TextLinkStyles(
                        style = SpanStyle(
                            color = colorSheme.linkColor,
                            textDecoration = TextDecoration.Underline,
                        ),
                    ),
                    linkInteractionListener = { link ->
                        (link as? LinkAnnotation.Clickable)?.tag?.let { customUrl ->
                            linkHandler?.handleCustomUrl(customUrl = customUrl)
                        }
                    },
                )
            } else {
                annotation
            }
            AnnotatedString.Range(
                item = updatedAnnotation,
                start = range.start,
                end = range.end,
                tag = range.tag,
            )
        }
    }


    var textLayoutResult: TextLayoutResult? = null

    Text(
        modifier = modifier
            .pointerInput(key1 = Unit) {
                detectTapGestures { offset ->
                    textLayoutResult?.let { layoutResult ->
                        val position = layoutResult.getOffsetForPosition(offset)
                        val annotation =
                            text.text.getLinkAnnotations(position, position).firstOrNull()
                        if (annotation?.item is LinkAnnotation.Url) {
                            val url = (annotation.item as LinkAnnotation.Url).url
                            if (url.startsWith("jetarticle://")) {
                                linkHandler?.handleCustomUrl(url)
                            }
                        }
                    }
                }
            }
            .padding(
                top = if (text.isTitle) dimensions.titleTopLinePadding else 0.dp,
                start = dimensions.startPadding,
                end = dimensions.endPadding,
            ),
        text = finalFormattedText,
        style = textStyle.merge(other = text.style.copy(color = color)),
        onTextLayout = {
            textLayoutResult = it
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun TextElementPreview() {
    MaterialTheme {
        CompositionLocalProvider(
            LocalArticleColorScheme provides articleColorScheme(),
            LocalArticleTypography provides articleTypography(),
        ) {
            TextElement(
                text = ArticleElement.Text(
                    text = AnnotatedString("Hello, World!"),
                    style = TextStyle.Default,
                    ids = emptyList(),
                    isTitle = false,
                    tag = "p",
                    sourceIndex = 0,
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleElementPreview() {
    MaterialTheme {
        CompositionLocalProvider(
            LocalArticleColorScheme provides articleColorScheme(),
            LocalArticleTypography provides articleTypography(),
        ) {
            TextElement(
                text = ArticleElement.Text(
                    text = AnnotatedString(text = "This is a title"),
                    style = TextStyle.Default,
                    ids = emptyList(),
                    isTitle = true,
                    tag = "h1",
                    sourceIndex = 0,
                )
            )
        }
    }
}
