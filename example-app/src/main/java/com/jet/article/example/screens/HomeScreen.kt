package com.jet.article.example.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.example.ui.components.ExampleScreenScaffold
import com.jet.article.example.ui.components.NavigationButton
import com.jet.article.example.ui.components.ScreenSection
import com.jet.article.example.ui.components.VerticalSpacer
import com.jet.article.example.ui.theme.JetarticleuiTheme

@Composable
internal fun HomeScreen(
    onHowToUseClick: () -> Unit,
    onCatalogClick: () -> Unit,
    onLoadCustomUrlClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ExampleScreenScaffold(
        title = "JetArticle",
        modifier = modifier,
        showBackButton = false,
    ) {
        ScreenSection(
            modifier = Modifier.fillMaxSize(),
        ) {
            Text(
                text = "Example app",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Explore the renderer, supported elements, and remote HTML loading.",
                style = MaterialTheme.typography.bodyLarge,
            )
            VerticalSpacer()
            NavigationButton(
                title = "How to use",
                description = "Render the hardcoded guide article.",
                onClick = onHowToUseClick,
            )
            VerticalSpacer()
            NavigationButton(
                title = "Catalog",
                description = "Browse examples for individual article elements.",
                onClick = onCatalogClick,
            )
            VerticalSpacer()
            NavigationButton(
                title = "Load custom URL",
                description = "Fetch HTML from a URL and render it as an article.",
                onClick = onLoadCustomUrlClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    JetarticleuiTheme {
        HomeScreen(
            onHowToUseClick = {},
            onCatalogClick = {},
            onLoadCustomUrlClick = {},
        )
    }
}
