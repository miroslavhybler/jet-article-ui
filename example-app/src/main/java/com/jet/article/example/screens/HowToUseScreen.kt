package com.jet.article.example.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.example.data.HOW_TO_USE_BASE_URL
import com.jet.article.example.data.HowToUseHtml
import com.jet.article.example.ui.components.ArticleHtmlContent
import com.jet.article.example.ui.components.ExampleScreenScaffold
import com.jet.article.example.ui.theme.JetarticleuiTheme

@Composable
internal fun HowToUseScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExampleScreenScaffold(
        title = "How to use",
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        ArticleHtmlContent(
            html = HowToUseHtml,
            baseUrl = HOW_TO_USE_BASE_URL,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HowToUseScreenPreview() {
    JetarticleuiTheme {
        HowToUseScreen(onBackClick = {})
    }
}
