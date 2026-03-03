package com.jet.article.ui.elements

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.ArticleTheme
import com.jet.article.ui.theme.LocalArticleColorScheme
import com.jet.article.ui.theme.LocalArticleDimensions
import com.jet.article.ui.theme.LocalArticleTypography
import com.jet.article.ui.theme.LocalArticleUrl
import com.jet.article.ui.theme.LocalLinkClickHandler
import com.jet.article.ui.theme.articleColorScheme
import com.jet.article.ui.theme.articleTypography

@Composable
fun TextElement(
    text: ArticleElement.Text,
    modifier: Modifier = Modifier,
) {
    val dimensions = LocalArticleDimensions.current
    val articleUrl = LocalArticleUrl.current
    val linkHandler = LocalLinkClickHandler.current

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

    ClickableText(
        modifier = modifier
            .let { originalModifier ->
                if (text.isTitle) {
                    originalModifier.padding(top = dimensions.titleTopLinePadding)
                } else originalModifier
            },
        text = text.text,
        style = textStyle.merge(other = text.style.copy(color = color)),
        onClick = { offset ->
            text.text.getStringAnnotations(tag = "URL", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    linkHandler?.handleLink(annotation.item, articleUrl)
                }
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
                    text = AnnotatedString("This is a title"),
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
