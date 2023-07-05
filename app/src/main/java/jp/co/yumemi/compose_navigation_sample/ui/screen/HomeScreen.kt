package jp.co.yumemi.compose_navigation_sample.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.compose_navigation_sample.R
import jp.co.yumemi.compose_navigation_sample.ui.theme.ComposenavigationsampleTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNext: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "MainScreen")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNext,
            ) {
                Icon(
                    Icons.Default.ArrowForward,
                    contentDescription = null,
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Hello, Compose Navigation")
            Image(
                modifier = Modifier.size(200.dp),
                painter = painterResource(id = R.drawable.ic_android),
                contentDescription = null,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    ComposenavigationsampleTheme {
        HomeScreen(
            onNext = {},
        )
    }
}