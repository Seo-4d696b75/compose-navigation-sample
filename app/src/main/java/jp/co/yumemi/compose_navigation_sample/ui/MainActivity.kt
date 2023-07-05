package jp.co.yumemi.compose_navigation_sample.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import jp.co.yumemi.compose_navigation_sample.ui.screen.Screen
import jp.co.yumemi.compose_navigation_sample.ui.screen.form.FormScreen
import jp.co.yumemi.compose_navigation_sample.ui.screen.form.FormViewModel
import jp.co.yumemi.compose_navigation_sample.ui.screen.home.HomeScreen
import jp.co.yumemi.compose_navigation_sample.ui.theme.ComposenavigationsampleTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposenavigationsampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Home.route,
                    ) {
                        composable(
                            route = Screen.Home.route,
                        ) {
                            HomeScreen(
                                onNext = {
                                    navController.navigate(Screen.Form.route)
                                },
                            )
                        }

                        composable(
                            route = Screen.Form.route,
                        ) {
                            val viewModel: FormViewModel = hiltViewModel()
                            val owner = LocalLifecycleOwner.current
                            val context = LocalContext.current
                            LaunchedEffect(viewModel) {
                                viewModel
                                    .navigationEvent
                                    .flowWithLifecycle(owner.lifecycle)
                                    .onEach { arg ->
                                        val intent =
                                            Intent(context, ResultActivity::class.java).apply {
                                                putExtra("arg", Json.encodeToString(arg))
                                            }
                                        context.startActivity(intent)
                                    }
                                    .launchIn(owner.lifecycleScope)
                            }
                            FormScreen(
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}
