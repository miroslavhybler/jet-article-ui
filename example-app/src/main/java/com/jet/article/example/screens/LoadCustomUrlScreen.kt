package com.jet.article.example.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jet.article.core.ArticleContentTransformer
import com.jet.article.core.ArticleData
import com.jet.article.example.ui.components.ExampleScreenScaffold
import com.jet.article.example.ui.components.ScreenSection
import com.jet.article.example.ui.components.VerticalSpacer
import com.jet.article.example.ui.theme.JetarticleuiTheme
import com.jet.article.ui.ArticleLazyColumn
import com.jet.article.ui.rememberArticleState
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
internal fun LoadCustomUrlScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val articleState = rememberArticleState()
    val coroutineScope = rememberCoroutineScope()
    var url by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    ExampleScreenScaffold(
        title = "Load custom URL",
        modifier = modifier,
        onBackClick = onBackClick,
    ) {
        ScreenSection {
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "URL") },
                placeholder = { Text(text = "https://example.com/article") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
            )
            VerticalSpacer()
            Button(
                onClick = {
                    coroutineScope.launch {
                        isLoading = true
                        errorMessage = null
                        try {
                            val data = loadArticleData(url)
                            articleState.data = data
                        } catch (throwable: Throwable) {
                            if (throwable is CancellationException) {
                                throw throwable
                            }
                            errorMessage = throwable.message ?: "HTML could not be loaded."
                        } finally {
                            isLoading = false
                        }
                    }
                },
                enabled = !isLoading,
                shape = RoundedCornerShape(8.dp),
            ) {
                Text(text = "Load")
            }
            if (isLoading) {
                VerticalSpacer()
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            if (errorMessage != null) {
                VerticalSpacer()
                Text(
                    text = errorMessage.orEmpty(),
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }

        if (articleState.data.isEmpty && !isLoading) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Enter a URL to render remote HTML.",
                    style = MaterialTheme.typography.bodyLarge,
                )
            }
        } else if (isLoading && articleState.data.isEmpty) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator()
            }
        } else {
            ArticleLazyColumn(
                state = articleState,
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
            )
        }
    }
}

private suspend fun loadArticleData(urlInput: String): ArticleData {
    val url = normalizeUrl(urlInput)
    require(url.isNotBlank()) { "Enter a URL." }

    val html = fetchHtml(url)
    return withContext(Dispatchers.Default) {
        ArticleContentTransformer().transform(html, url)
    }
}

private suspend fun fetchHtml(url: String): String {
    return withContext(Dispatchers.IO) {
        val client = HttpClient(Android)
        try {
            client.get(url).bodyAsText()
        } finally {
            client.close()
        }
    }
}

private fun normalizeUrl(input: String): String {
    val trimmed = input.trim()
    if (trimmed.isBlank()) {
        return ""
    }

    val lower = trimmed.lowercase()
    return if (lower.startsWith("http://") || lower.startsWith("https://")) {
        trimmed
    } else {
        "https://$trimmed"
    }
}

@Preview(showBackground = true)
@Composable
private fun LoadCustomUrlScreenPreview() {
    JetarticleuiTheme {
        LoadCustomUrlScreen(onBackClick = {})
    }
}
