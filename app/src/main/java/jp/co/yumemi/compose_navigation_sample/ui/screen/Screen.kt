package jp.co.yumemi.compose_navigation_sample.ui.screen

import android.net.Uri
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument
import jp.co.yumemi.compose_navigation_sample.ui.screen.result.ResultArg
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

sealed interface Screen {
    val route: String

    object Home : Screen {
        override val route = "home"
    }

    object Form : Screen {
        override val route = "form"
    }

    sealed interface Result : Screen {
        object A : Result {
            override val route = "result/a/{resultArgA}"
        }

        object B : Result {
            override val route = "result/b/{resultArgB}"
        }
    }
}

fun resultArgA(defaultArg: ResultArg.A? = null) = navArgument("resultArgA") {
    type = NavType.StringType
    defaultArg?.let {
        defaultValue = Uri.encode(Json.encodeToString(it))
    }
}

fun resultArgB(defaultArg: ResultArg.B? = null) = navArgument("resultArgB") {
    type = NavType.StringType
    defaultArg?.let {
        defaultValue = Uri.encode(Json.encodeToString(it))
    }
}

val NavBackStackEntry.resultArgA: ResultArg.A?
    get() = arguments?.getString("resultArgA")?.let {
        Json.decodeFromString(Uri.decode(it))
    }

val NavBackStackEntry.resultArgB: ResultArg.B?
    get() = arguments?.getString("resultArgB")?.let {
        Json.decodeFromString(Uri.decode(it))
    }

