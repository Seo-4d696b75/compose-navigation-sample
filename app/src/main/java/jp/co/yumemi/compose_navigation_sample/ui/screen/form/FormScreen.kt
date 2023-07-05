package jp.co.yumemi.compose_navigation_sample.ui.screen.form

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import jp.co.yumemi.compose_navigation_sample.ui.theme.ComposenavigationsampleTheme

@Composable
fun FormScreen(
    modifier: Modifier = Modifier,
    viewModel: FormViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    FormScreen(
        text = state.text,
        check = state.check,
        select = state.select,
        onTextChanged = viewModel::onTextChanged,
        onCheckChanged = viewModel::onCheckChanged,
        onSelectChanged = viewModel::onSelectChanged,
        showResultA = viewModel::showResultA,
        showResultB = viewModel::showResultB,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    text: String,
    check: Boolean,
    select: FavoriteSelect,
    onTextChanged: (String) -> Unit,
    onCheckChanged: (Boolean) -> Unit,
    onSelectChanged: (FavoriteSelect) -> Unit,
    showResultA: () -> Unit,
    showResultB: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FormScreen")
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(32.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = onTextChanged,
                label = { Text(text = "Name") },
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable(
                    onClick = { onCheckChanged(!check) }
                )
            ) {
                Checkbox(
                    checked = check,
                    onCheckedChange = null,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Familiar with Compose?")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Which do you like?")
            Column {
                FavoriteSelect.values().forEach { favorite ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable(
                            onClick = { onSelectChanged(favorite) }
                        ).padding(4.dp)
                    ) {
                        RadioButton(
                            selected = select == favorite,
                            onClick = null,
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = favorite.displayedName)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                text = "Select next screen",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleMedium,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                ElevatedButton(onClick = showResultA) {
                    Text(text = "A")
                }
                Spacer(modifier = Modifier.width(32.dp))
                ElevatedButton(onClick = showResultB) {
                    Text(text = "B")
                }
            }
        }
    }
}

@Preview
@Composable
private fun PreviewFormScreen() {
    ComposenavigationsampleTheme {
        FormScreen(
            text = "",
            check = false,
            select = FavoriteSelect.None,
            onTextChanged = {},
            onCheckChanged = {},
            onSelectChanged = {},
            showResultA = {},
            showResultB = {},
        )
    }
}