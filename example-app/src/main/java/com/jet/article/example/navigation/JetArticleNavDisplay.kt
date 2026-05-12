package com.jet.article.example.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.jet.article.example.data.findCatalogSample
import com.jet.article.example.screens.CatalogElementScreen
import com.jet.article.example.screens.CatalogScreen
import com.jet.article.example.screens.HomeScreen
import com.jet.article.example.screens.HowToUseScreen
import com.jet.article.example.screens.LoadCustomUrlScreen
import com.jet.article.example.ui.theme.JetarticleuiTheme

@Composable
internal fun JetArticleNavDisplay(
    modifier: Modifier = Modifier,
) {
    val backStack = remember { mutableStateListOf<ExampleRoute>(ExampleRoute.Home) }
    val navigateBack = {
        if (backStack.size > 1) {
            backStack.removeLastOrNull()
        }
    }

    NavDisplay(
        backStack = backStack,
        modifier = modifier,
        onBack = navigateBack,
        entryProvider = { route ->
            when (route) {
                ExampleRoute.Home -> NavEntry(route) {
                    HomeScreen(
                        onHowToUseClick = { backStack.add(ExampleRoute.HowToUse) },
                        onCatalogClick = { backStack.add(ExampleRoute.Catalog) },
                        onLoadCustomUrlClick = { backStack.add(ExampleRoute.LoadCustomUrl) },
                    )
                }

                ExampleRoute.HowToUse -> NavEntry(route) {
                    HowToUseScreen(onBackClick = navigateBack)
                }

                ExampleRoute.Catalog -> NavEntry(route) {
                    CatalogScreen(
                        onBackClick = navigateBack,
                        onSampleClick = { sampleId ->
                            backStack.add(ExampleRoute.CatalogElement(sampleId))
                        },
                    )
                }

                ExampleRoute.LoadCustomUrl -> NavEntry(route) {
                    LoadCustomUrlScreen(onBackClick = navigateBack)
                }

                is ExampleRoute.CatalogElement -> NavEntry(route) {
                    CatalogElementScreen(
                        sample = findCatalogSample(route.sampleId),
                        onBackClick = navigateBack,
                    )
                }
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun JetArticleNavDisplayPreview() {
    JetarticleuiTheme {
        JetArticleNavDisplay()
    }
}
