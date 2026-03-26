package com.jet.article.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.LocalArticleDimensions
import com.jet.article.ui.theme.ArticleTheme
import com.jet.article.ui.theme.LocalArticleColorScheme
import com.jet.article.ui.theme.articleColorScheme

@Composable
fun Quote(
    modifier: Modifier = Modifier,
    quote: ArticleElement.Quote,
    shape: Shape = MaterialTheme.shapes.medium,
    lineShape: Shape = CircleShape,
) {
    val dimensions = LocalArticleDimensions.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = shape,
        color = ArticleTheme.colorScheme.quoteContainerColor,
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(space = dimensions.spaceBetweenQuoteAndVerticalLine),
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(width =dimensions.quoteLineWidth)
                    .background(
                        color = ArticleTheme.colorScheme.quoteVerticalLineColor,
                        shape = lineShape,
                    )
                    .clip(shape = lineShape)
            )

            Text(
                modifier = Modifier
                    .padding(vertical = dimensions.quoteVerticalPadding),
                text = quote.text,
                style = MaterialTheme.typography.bodyMedium,
                color = ArticleTheme.colorScheme.quoteTextColor,

                )

        }
    }
}


@Composable
@Preview(showBackground = true)
private fun QuotePreview() {
    CompositionLocalProvider(
        LocalArticleColorScheme provides articleColorScheme()
    ) {
        Quote(
            quote = ArticleElement.Quote(
                text = AnnotatedString(
                    text = "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Suspendisse nisl. Quisque porta. Curabitur ligula sapien, pulvinar a vestibulum quis, facilisis vel sapien. Nullam sit amet magna in magna gravida vehicula. Morbi imperdiet, mauris ac auctor dictum, nisl ligula egestas nulla, et sollicitudin sem purus in lacus. Etiam ligula pede, sagittis quis, interdum ultricies, scelerisque eu. Mauris dolor felis, sagittis at, luctus sed, aliquam non, tellus. Ut tempus purus at lorem. Integer imperdiet lectus quis justo. Phasellus faucibus molestie nisl. Morbi scelerisque luctus velit. Phasellus et lorem id felis nonummy placerat."
                ),
                ids = emptyList(),
                style = TextStyle(),
                sourceIndex = 0,
            )
        )
    }

}