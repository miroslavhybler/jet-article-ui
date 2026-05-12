package com.jet.article.example.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.example.data.CatalogSample
import com.jet.article.example.data.CatalogSamples
import com.jet.article.example.ui.components.ArticleHtmlContent
import com.jet.article.example.ui.components.ExampleScreenScaffold
import com.jet.article.example.ui.theme.JetarticleuiTheme

@Composable
internal fun CatalogElementScreen(
    sample: CatalogSample,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExampleScreenScaffold(
        title = sample.title,
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        ArticleHtmlContent(
            html = sample.html,
            baseUrl = sample.baseUrl,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CatalogElementScreenPreview() {
    JetarticleuiTheme {
        CatalogElementScreen(
            sample = CatalogSamples.first(),
            onBackClick = {},
        )
    }
}
