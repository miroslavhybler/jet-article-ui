package com.jet.article.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jet.article.core.ArticleData

class ArticleState internal constructor(
    initialData: ArticleData,
) {
    var data by mutableStateOf(value = initialData)

}


@Composable
fun rememberArticleState(
    initialData: ArticleData = ArticleData.Empty,
): ArticleState {
    return remember {
        ArticleState(
            initialData = initialData,
        )
    }
}
