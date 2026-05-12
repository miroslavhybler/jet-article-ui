package com.jet.article.example.navigation

internal sealed interface ExampleRoute {
    data object Home : ExampleRoute
    data object HowToUse : ExampleRoute
    data object Catalog : ExampleRoute
    data object LoadCustomUrl : ExampleRoute
    data class CatalogElement(val sampleId: String) : ExampleRoute
}
