package com.jet.article.ui

import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.jet.article.ui.LinkClickHandler
import com.jet.article.ui.theme.ArticleColorScheme
import com.jet.article.ui.theme.ArticleDimensions
import com.jet.article.ui.theme.ArticleTypography
import com.jet.article.ui.theme.LocalArticleColorScheme
import com.jet.article.ui.theme.LocalArticleDimensions
import com.jet.article.ui.theme.LocalArticleTypography
import com.jet.article.ui.theme.LocalArticleUrl
import com.jet.article.ui.theme.LocalLinkClickHandler
import com.jet.article.ui.theme.articleColorScheme
import com.jet.article.ui.theme.articleTypography


/**
 * Basic container to provide local providers for the items ui.
 * @author Miroslav Hýbler <br>
 * created on 23.02.2026
 */
@Composable
fun ArticleContainer(
    articleUrl: String?,
    colorScheme: ArticleColorScheme,
    typography: ArticleTypography,
    dimensions: ArticleDimensions,
    linkClickHandler: LinkClickHandler,
    content: @Composable () -> Unit,
) {



    CompositionLocalProvider(
        LocalArticleColorScheme provides colorScheme,
        LocalArticleTypography provides typography,
        LocalArticleDimensions provides dimensions,
        LocalArticleUrl provides articleUrl,
        LocalLinkClickHandler provides linkClickHandler,
        ) {
        SelectionContainer {
            content()
        }
    }
}