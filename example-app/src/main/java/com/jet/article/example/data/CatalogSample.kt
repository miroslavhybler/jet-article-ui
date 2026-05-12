package com.jet.article.example.data

internal data class CatalogSample(
    val id: String,
    val title: String,
    val description: String,
    val baseUrl: String,
    val html: String,
)

internal val CatalogSamples = listOf(
    CatalogSample(
        id = "headings",
        title = "Headings",
        description = "h1 to h6 hierarchy and spacing.",
        baseUrl = "https://example.com/catalog/headings",
        html = """
            <h1>Heading level one</h1>
            <p>Use headings to create a clear article outline.</p>
            <h2>Heading level two</h2>
            <p>Sections can contain regular paragraphs between titles.</p>
            <h3>Heading level three</h3>
            <h4>Heading level four</h4>
            <h5>Heading level five</h5>
            <h6>Heading level six</h6>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "paragraphs",
        title = "Paragraphs and Text",
        description = "Paragraphs with bold, italic, underline, and inline quotes.",
        baseUrl = "https://example.com/catalog/paragraphs",
        html = """
            <h1>Inline text</h1>
            <p>This paragraph contains <strong>strong text</strong>, <b>bold text</b>, <em>emphasis</em>, <i>italic text</i>, and <u>underlined text</u>.</p>
            <p>Inline quotes are supported too: <q>Compose makes UI state visible.</q></p>
            <p>Line breaks can be inserted with a br tag.<br>Everything after the break continues in the same content block.</p>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "images",
        title = "Images",
        description = "Remote image loading through the article UI.",
        baseUrl = "https://picsum.photos",
        html = """
            <h1>Image</h1>
            <p>Images can be embedded as part of the article flow.</p>
            <img src="https://picsum.photos/900/480" alt="Random landscape" />
            <p>The renderer keeps the image inside the reading column.</p>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "quotes",
        title = "Quotes",
        description = "Blockquote rendering for cited or highlighted content.",
        baseUrl = "https://example.com/catalog/quotes",
        html = """
            <h1>Quote</h1>
            <p>Block quotes are useful for excerpts and callouts.</p>
            <blockquote>
                A good article renderer should let content breathe while still respecting app design.
            </blockquote>
            <p>The content continues after the quote block.</p>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "lists",
        title = "Lists",
        description = "Ordered and unordered content lists.",
        baseUrl = "https://example.com/catalog/lists",
        html = """
            <h1>Lists</h1>
            <p>Use unordered lists for related items.</p>
            <ul>
                <li>Headers</li>
                <li>Paragraphs</li>
                <li>Images</li>
            </ul>
            <p>Use ordered lists for steps.</p>
            <ol>
                <li>Load HTML</li>
                <li>Transform it into article data</li>
                <li>Render it with JetArticle UI</li>
            </ol>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "tables",
        title = "Tables",
        description = "Basic table rows, headers, and cells.",
        baseUrl = "https://example.com/catalog/tables",
        html = """
            <h1>Table</h1>
            <table>
                <tr>
                    <th>Tag</th>
                    <th>Purpose</th>
                </tr>
                <tr>
                    <td>table</td>
                    <td>Container for tabular content</td>
                </tr>
                <tr>
                    <td>tr</td>
                    <td>Row in the table</td>
                </tr>
                <tr>
                    <td>td</td>
                    <td>Cell in a row</td>
                </tr>
            </table>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "code",
        title = "Code",
        description = "Preformatted blocks and inline code.",
        baseUrl = "https://example.com/catalog/code",
        html = """
            <h1>Code</h1>
            <p>Inline code like <code>ArticleLazyColumn</code> stays inside text.</p>
            <pre><code>@Composable
fun ArticleDemo(html: String) {
    val state = rememberArticleState()
    LaunchedEffect(html) {
        state.data = ArticleContentTransformer()
            .transform(html, "https://example.com")
    }
    ArticleLazyColumn(state = state)
}</code></pre>
        """.trimIndent(),
    ),
    CatalogSample(
        id = "links",
        title = "Links",
        description = "Section, same-domain, other-domain, and URI links.",
        baseUrl = "https://example.com/docs/",
        html = """
            <h1 id="top">Links</h1>
            <p>Jump to <a href="#details">the details section</a> with an in-page link.</p>
            <p>Open a <a href="/docs/page">same-domain page</a>, an <a href="https://github.com/miroslavhybler/jet-article-ui">external page</a>, or an <a href="mailto:hello@example.com">email link</a>.</p>
            <h2 id="details">Details</h2>
            <p>Section links can scroll to matching element ids.</p>
            <p><a href="#top">Back to top</a></p>
        """.trimIndent(),
    ),
)

internal fun findCatalogSample(sampleId: String): CatalogSample {
    return CatalogSamples.firstOrNull { it.id == sampleId } ?: CatalogSamples.first()
}
