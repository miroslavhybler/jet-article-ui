package com.jet.article.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

/**
 * A color scheme for styling article elements, designed to be used with [ArticleTheme].
 *
 * @property titleColor The color used for heading elements (h1-h6).
 * @property textColor The color used for standard paragraph text.
 * @property quoteContainerColor The background color for blockquote elements.
 * @property quoteTextColor The text color within blockquote elements.
 * @property quoteVerticalLineColor The color of the decorative vertical line in blockquote elements.
 * @property codeContainerColor The background color for code block elements.
 * @property codeContentColor The text color within code block elements.
 */
@Immutable
data class ArticleColorScheme constructor(
    val titleColor: Color,
    val textColor: Color,
    val linkColor: Color,
    val quoteContainerColor: Color,
    val quoteTextColor: Color,
    val quoteVerticalLineColor: Color,
    val codeContainerColor: Color,
    val codeContentColor: Color,
    val listBulletColor: Color,
)

/**
 * CompositionLocal that provides the [ArticleColorScheme] for the `Article` composables.
 */
val LocalArticleColorScheme = staticCompositionLocalOf {
    ArticleColorScheme(
        titleColor = Color.Unspecified,
        textColor = Color.Unspecified,
        linkColor = Color.Unspecified,
        quoteContainerColor = Color.Unspecified,
        quoteTextColor = Color.Unspecified,
        quoteVerticalLineColor = Color.Unspecified,
        codeContainerColor = Color.Unspecified,
        codeContentColor = Color.Unspecified,
        listBulletColor= Color.Unspecified,
    )
}

/**
 * A set of text styles for styling article elements, designed to be used with [ArticleTheme].
 *
 * @property h1 The [TextStyle] for `<h1>` HTML tags.
 * @property h2 The [TextStyle] for `<h2>` HTML tags.
 * @property h3 The [TextStyle] for `<h3>` HTML tags.
 * @property h4 The [TextStyle] for `<h4>` HTML tags.
 * @property h5 The [TextStyle] for `<h5>` HTML tags.
 * @property h6 The [TextStyle] for `<h6>` HTML tags.
 * @property text The [TextStyle] for `<p>` HTML tags.
 * @property quote The [TextStyle] for `<blockquote>` HTML tags.
 * @property code The [TextStyle] for `<code>` and `<pre>` HTML tags.
 */
@Immutable
data class ArticleTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val text: TextStyle,
    val quote: TextStyle,
    val code: TextStyle,
)

/**
 * CompositionLocal that provides the [ArticleTypography] for the `Article` composables.
 */
val LocalArticleTypography = staticCompositionLocalOf {
    ArticleTypography(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        h4 = TextStyle.Default,
        h5 = TextStyle.Default,
        h6 = TextStyle.Default,
        text = TextStyle.Default,
        quote = TextStyle.Default,
        code = TextStyle.Default,
    )
}

/**
 * Provides access to the current [ArticleColorScheme] and [ArticleTypography] for styling
 * `Article` composables. Values are provided via [LocalArticleColorScheme] and
 * [LocalArticleTypography].
 */
object ArticleTheme {
    /**
     * Retrieves the current [ArticleColorScheme] from the composition.
     */
    val colorScheme: ArticleColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalArticleColorScheme.current

    /**
     * Retrieves the current [ArticleTypography] from the composition.
     */
    val typography: ArticleTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalArticleTypography.current
}

/**
 * Creates a default [ArticleColorScheme] that derives its colors from the current
 * [MaterialTheme.colorScheme]. This is the default color scheme used by `Article` composables.
 */
@Composable
fun articleColorScheme() = ArticleColorScheme(
    titleColor = MaterialTheme.colorScheme.primary,
    textColor = MaterialTheme.colorScheme.onBackground,
    linkColor = MaterialTheme.colorScheme.primary,
    quoteContainerColor = MaterialTheme.colorScheme.surfaceVariant,
    quoteTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
    quoteVerticalLineColor = MaterialTheme.colorScheme.tertiary,
    codeContainerColor = MaterialTheme.colorScheme.secondaryContainer,
    codeContentColor = MaterialTheme.colorScheme.onSecondaryContainer,
    listBulletColor = MaterialTheme.colorScheme.primary,
)

/**
 * Creates a default [ArticleTypography] that derives its text styles from the current
 * [MaterialTheme.typography]. This is the default typography used by `Article` composables.
 *
 * @param typography The Material [Typography] to retrieve the styles from. Defaults to `MaterialTheme.typography`.
 */
@Composable
fun articleTypography(
    typography: Typography = MaterialTheme.typography,
): ArticleTypography {
    return articleTypography(
        h1 = typography.displayLarge,
        h2 = typography.displayMedium,
        h3 = typography.displaySmall,
        h4 = typography.headlineLarge,
        h5 = typography.headlineMedium,
        h6 = typography.headlineSmall,
        text = typography.bodyLarge,
        quote = typography.bodyLarge,
        code = typography.bodySmall,
    )
}

/**
 * Creates an [ArticleTypography] with customized text styles.
 *
 * This function allows for overriding specific text styles one by one, falling back to the
 * default styles derived from [MaterialTheme.typography] for any parameters not provided.
 *
 * @param h1 The [TextStyle] for `<h1>` HTML tags.
 * @param h2 The [TextStyle] for `<h2>` HTML tags.
 * @param h3 The [TextStyle] for `<h3>` HTML tags.
 * @param h4 The [TextStyle] for `<h4>` HTML tags.
 * @param h5 The [TextStyle] for `<h5>` HTML tags.
 * @param h6 The [TextStyle] for `<h6>` HTML tags.
 * @param text The [TextStyle] for `<p>` HTML tags.
 * @param quote The [TextStyle] for `<blockquote>` HTML tags.
 * @param code The [TextStyle] for `<code>` and `<pre>` HTML tags.
 */
@Composable
fun articleTypography(
    h1: TextStyle = MaterialTheme.typography.displayLarge,
    h2: TextStyle = MaterialTheme.typography.displayMedium,
    h3: TextStyle = MaterialTheme.typography.displaySmall,
    h4: TextStyle = MaterialTheme.typography.headlineLarge,
    h5: TextStyle = MaterialTheme.typography.headlineMedium,
    h6: TextStyle = MaterialTheme.typography.headlineSmall,
    text: TextStyle = MaterialTheme.typography.bodyLarge,
    quote: TextStyle = MaterialTheme.typography.bodyLarge,
    code: TextStyle = MaterialTheme.typography.bodySmall,
): ArticleTypography = ArticleTypography(
    h1 = h1,
    h2 = h2,
    h3 = h3,
    h4 = h4,
    h5 = h5,
    h6 = h6,
    text = text,
    quote = quote,
    code = code,
)
