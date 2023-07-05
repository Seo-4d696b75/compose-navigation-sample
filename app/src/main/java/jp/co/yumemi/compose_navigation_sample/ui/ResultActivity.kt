package jp.co.yumemi.compose_navigation_sample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import jp.co.yumemi.compose_navigation_sample.ui.screen.Screen
import jp.co.yumemi.compose_navigation_sample.ui.screen.result.ResultAScreen
import jp.co.yumemi.compose_navigation_sample.ui.screen.result.ResultArg
import jp.co.yumemi.compose_navigation_sample.ui.screen.result.ResultBScreen
import jp.co.yumemi.compose_navigation_sample.ui.screen.resultArgA
import jp.co.yumemi.compose_navigation_sample.ui.screen.resultArgB
import jp.co.yumemi.compose_navigation_sample.ui.theme.ComposenavigationsampleTheme
import kotlinx.serialization.json.Json

class ResultActivity : ComponentActivity() {

    private val arg: ResultArg by lazy {
        val str = intent.getStringExtra("arg") ?: throw IllegalArgumentException("arg not found")
        Json.decodeFromString(str)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposenavigationsampleTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val startScreen = remember {
                        when (arg) {
                            is ResultArg.A -> Screen.Result.A
                            is ResultArg.B -> Screen.Result.B
                        }
                    }
                    NavHost(
                        navController = navController,
                        startDestination = startScreen.route,
                    ) {
                        composable(
                            route = Screen.Result.A.route,
                            arguments = listOf(resultArgA(arg as? ResultArg.A))
                        ) { entry ->
                            ResultAScreen(
                                arg = requireNotNull(entry.resultArgA)
                            )
                        }
                        composable(
                            route = Screen.Result.B.route,
                            arguments = listOf(resultArgB(arg as? ResultArg.B))
                        ) { entry ->
                            ResultBScreen(
                                arg = requireNotNull(entry.resultArgB)
                            )
                        }
                    }
                }
            }
        }
    }
}