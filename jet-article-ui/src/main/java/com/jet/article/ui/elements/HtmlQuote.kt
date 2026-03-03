package com.jet.article.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleElement
import com.jet.article.ui.theme.LocalArticleDimensions
import com.jet.article.ui.theme.ArticleTheme

@Composable
fun QuoteContainer(
    modifier: Modifier = Modifier,
    quote: ArticleElement.Quote,
    shape: Shape = MaterialTheme.shapes.medium,
) {
    val dimensions = LocalArticleDimensions.current

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = shape,
        color = ArticleTheme.colorScheme.quoteContainerColor,
    ) {
        Row {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(dimensions.quoteLineWidth)
                    .background(ArticleTheme.colorScheme.quoteVerticalLineColor)
            )

            Spacer(modifier = Modifier.width(width = 12.dp))

            Text(
                text = quote.text,
                style = MaterialTheme.typography.bodyMedium,
                color = ArticleTheme.colorScheme.quoteTextColor,
                modifier = Modifier
                    .padding(vertical = dimensions.quoteVerticalPadding),
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun QuotePreview() {
    QuoteContainer(
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