package com.jet.article.example.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.example.data.CatalogSamples
import com.jet.article.example.ui.components.ExampleScreenScaffold
import com.jet.article.example.ui.components.NavigationButton
import com.jet.article.example.ui.components.ScreenSection
import com.jet.article.example.ui.components.VerticalSpacer
import com.jet.article.example.ui.theme.JetarticleuiTheme

@Composable
internal fun CatalogScreen(
    onBackClick: () -> Unit,
    onSampleClick: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    ExampleScreenScaffold(
        title = "Catalog",
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        ScreenSection(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            CatalogSamples.forEachIndexed { index, sample ->
                if (index > 0) {
                    VerticalSpacer()
                }
                NavigationButton(
                    title = sample.title,
                    description = sample.description,
                    onClick = { onSampleClick(sample.id) },
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CatalogScreenPreview() {
    JetarticleuiTheme {
        CatalogScreen(
            onBackClick = {},
            onSampleClick = {},
        )
    }
}
