package com.jet.article.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.jet.article.example.navigation.JetArticleNavDisplay
import com.jet.article.example.ui.theme.JetarticleuiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetarticleuiTheme {
                JetArticleExampleApp()
            }
        }
    }
}

@Composable
private fun JetArticleExampleApp() {
    JetArticleNavDisplay()
}

@Preview(showBackground = true)
@Composable
private fun JetArticleExampleAppPreview() {
    JetarticleuiTheme {
        JetArticleExampleApp()
    }
}
