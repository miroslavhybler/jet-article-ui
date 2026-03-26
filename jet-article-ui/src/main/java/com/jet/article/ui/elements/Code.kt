package com.jet.article.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.ArticleTheme
import com.jet.article.ui.theme.LocalArticleDimensions

@Composable
fun Code(
    text: ArticleElement.Text,
    modifier: Modifier = Modifier,
    shape: Shape = MaterialTheme.shapes.small,
) {
    val dimensions = LocalArticleDimensions.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = ArticleTheme.colorScheme.codeContainerColor, shape = shape)
            .padding(paddingValues = dimensions.codePaddingValues)
    ) {
        Text(
            text = text.text,
            style = ArticleTheme.typography.code,
            color = ArticleTheme.colorScheme.codeContentColor
        )
    }
}