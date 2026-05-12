package com.jet.article.example.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleContentTransformer
import com.jet.article.ui.ArticleLazyColumn
import com.jet.article.ui.rememberArticleState

@Composable
internal fun ArticleHtmlContent(
    modifier: Modifier = Modifier,
    html: String,
    baseUrl: String,
) {
    val articleData = remember(key1 = html, key2 = baseUrl) {
        runCatching {
            ArticleContentTransformer().transform(html = html, url = baseUrl)
        }
    }

    articleData.fold(
        onSuccess = { data ->
            key(html, baseUrl) {
                ArticleLazyColumn(
                    state = rememberArticleState(initialData = data),
                    modifier = modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(space = 24.dp),
                )
            }
        },
        onFailure = { throwable ->
            Text(
                text = throwable.message ?: "Article content could not be rendered.",
                modifier = modifier,
                color = MaterialTheme.colorScheme.error,
            )
        },
    )
}
