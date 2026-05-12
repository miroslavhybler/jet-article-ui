package com.jet.article.example.data

internal const val HOW_TO_USE_BASE_URL = "https://github.com/miroslavhybler/jet-article-ui"

internal val HowToUseHtml: String = """
    <h1>About JetArticle</h1>
    <p>JetArticle is a powerful Android library that allows you to easily render HTML content within your Jetpack Compose applications. It provides a flexible and customizable way to display articles, blog posts, or any other HTML-based content, while seamlessly integrating with your app's design.</p>
    
    <h2>Table of Contents</h2>
    <ul>
        <li><a href="#how-to-use">How to Use</a></li>
        <li><a href="#customization">Customization</a></li>
        <li><a href="#supported-tags">Supported Tags</a></li>
        <li><a href="#links">Links</a></li>
    </ul>

    <h2 id="how-to-use">How to Use</h2>
    <p>To use JetArticle, you first need to add the dependency to your project's build.gradle file:</p>
    <pre class="code"><code>dependencies {
    implementation("com.jet.article:jet-article-ui:1.0.0")
}</code></pre>
    
    <p>Next, you can use the <code>ArticleColumn</code> or <code>ArticleLazyColumn</code> composable in your UI. Here's a simple example:</p>
    <pre class="code"><code id="code-1">@Composable
fun MyScreen() {
    val htmlContent = "&lt;h1&gt;Hello, JetArticle!&lt;/h1&gt;&lt;p&gt;This is a sample article.&lt;/p&gt;"
    val articleState = rememberArticleState()
    
    LaunchedEffect(key1 = Unit) {
        articleState.data = ArticleContentTransformer().transform(
            html = htmlContent,
            url = "https://example.com",
        )
    }
    
    ArticleLazyColumn(state = articleState)
}</code></pre>
    
    <h3 id="customization">Customization</h3>
    <p>JetArticle offers extensive customization options through composable parameters. You can modify the appearance of various HTML elements, such as headers, paragraphs, images, and more by providing your own composables.</p>
    
    <h4 id="supported-tags">Supported Tags</h4>
    <p>JetArticle supports a wide range of HTML tags. The library processes these tags to build a structured representation of the content. While it recognizes block-level tags to structure the content, it does not replicate all of their specific behaviors. For example, a &lt;div&gt; tag is processed for its content, but it does not create a separate layout block in the same way it would in a web browser.</p>
    <ul>
        <li>&lt;h1&gt; to &lt;h6&gt; for headers</li>
        <li>&lt;p&gt; for paragraphs</li>
        <li>&lt;div&gt; for content division</li>
        <li>&lt;img&gt; for images</li>
        <li>&lt;br&gt; for line breaks</li>
        <li>&lt;pre&gt; for preformatted text</li>
        <li>&lt;blockquote&gt; for quote blocks</li>
        <li>&lt;ul&gt;, &lt;ol&gt;, &lt;li&gt; for lists</li>
        <li>&lt;table&gt;, &lt;tr&gt;, &lt;td&gt;, &lt;th&gt; for tables</li>
        <li>&lt;b&gt;, &lt;strong&gt; for bold text</li>
        <li>&lt;i&gt;, &lt;em&gt;, &lt;address&gt; for italicized text</li>
        <li>&lt;u&gt; for underlined text</li>
        <li>&lt;a&gt; for links</li>
        <li>&lt;q&gt; for inline quotes</li>
    </ul>
    
    <p>With JetArticle, you can easily create beautiful and engaging reading experiences in your Android apps. Happy coding!</p>

    <h4 id="links">Links</h4>
    <p>For more information, you can visit the public repositories on GitHub:</p>
    <ul>
        <li><a href="https://github.com/miroslavhybler/jet-article-core">jet-article-core</a></li>
        <li><a href="https://github.com/miroslavhybler/jet-article-ui">jet-article-ui</a></li>
    </ul>
""".trimIndent()
