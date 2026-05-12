package com.jet.article.example.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
internal fun ExampleScreenScaffold(
    title: String,
    modifier: Modifier = Modifier,
    showBackButton: Boolean = true,
    onBackClick: () -> Unit = {},
    content: @Composable ColumnScope.() -> Unit,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Surface(
                tonalElevation = 2.dp,
                shadowElevation = 1.dp,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .statusBarsPadding()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (showBackButton) {
                        TextButton(onClick = onBackClick) {
                            Text(text = "Back")
                        }
                    }
                    Text(
                        text = title,
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = if (showBackButton) 8.dp else 0.dp),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.SemiBold,
                    )
                }
            }
        },
    ) { innerPadding: PaddingValues ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.fillMaxSize(),
                content = content,
            )
        }
    }
}

@Composable
internal fun ScreenSection(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        content = content,
    )
}

@Composable
internal fun VerticalSpacer() {
    Spacer(modifier = Modifier.height(12.dp))
}
