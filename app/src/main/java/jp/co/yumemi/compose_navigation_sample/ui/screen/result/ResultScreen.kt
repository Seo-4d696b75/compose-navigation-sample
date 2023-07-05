package jp.co.yumemi.compose_navigation_sample.ui.screen.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jp.co.yumemi.compose_navigation_sample.R
import jp.co.yumemi.compose_navigation_sample.ui.screen.form.FavoriteSelect
import jp.co.yumemi.compose_navigation_sample.ui.theme.ComposenavigationsampleTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultAScreen(
    arg: ResultArg.A,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ResultScreen (A)")
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hi, ${arg.name}",
                style = MaterialTheme.typography.titleLarge,
            )
            if (arg.isComposeMaster) {
                Text(text = "You are Compose master!")
            } else {
                Text(text = "You have to learn Compose")
            }
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.android_compose),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
            Text(text = "(checked: ${arg.isComposeMaster})")
        }
    }
}

@Preview
@Composable
private fun PreviewResultAScreen() {
    ComposenavigationsampleTheme {
        ResultAScreen(
            arg = ResultArg.A(
                name = "田中タロウ",
                isComposeMaster = true,
            )
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultBScreen(
    arg: ResultArg.B,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "ResultScreen (B)")
                }
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Hi, ${arg.name}",
                style = MaterialTheme.typography.titleLarge,
            )
            Text(text = "your select: ${arg.favorite.displayedName}")
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(16.dp),
                painter = painterResource(id = R.drawable.android_compose),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
            )
        }
    }
}

@Preview
@Composable
private fun PreviewResultBScreen() {
    ComposenavigationsampleTheme {
        ResultBScreen(
            arg = ResultArg.B(
                name = "田中タロウ",
                favorite = FavoriteSelect.View,
            )
        )
    }
}