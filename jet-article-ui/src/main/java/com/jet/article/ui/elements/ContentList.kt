package com.jet.article.ui.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import com.jet.article.core.ArticleElement
import com.jet.article.ui.R
import com.jet.article.ui.theme.ArticleDefaults
import com.jet.article.ui.theme.LocalArticleColorScheme

@Composable
fun ContentList(
    list: ArticleElement.ContentList,
    modifier: Modifier = Modifier,
    @DrawableRes bulletRes: Int = R.drawable.ic_jet_article_list_item,
    bulletTint: Color = LocalArticleColorScheme.current.listBulletColor,
    style: TextStyle = MaterialTheme.typography.bodyLarge,
    textContent: @Composable (text: ArticleElement.Text) -> Unit = { ArticleDefaults.Text(it) },
) {
    val density = LocalDensity.current

    Column(modifier = modifier.fillMaxWidth()) {
        list.items.fastForEach { item ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
            ) {
                Icon(
                    modifier = Modifier
                        .padding(end = 6.dp)
                        .size(with(receiver = density) { style.fontSize.toDp() }),
                    painter = painterResource(id = bulletRes),
                    contentDescription = null,
                    tint = bulletTint,
                )
                // For now, we only display the text content of the list items.
                item.forEach { element ->
                    if (element is ArticleElement.Text) {
                        textContent(element)
                    }
                }
            }
        }
    }
}
