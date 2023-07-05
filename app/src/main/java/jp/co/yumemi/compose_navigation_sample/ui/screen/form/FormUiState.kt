package jp.co.yumemi.compose_navigation_sample.ui.screen.form

import androidx.compose.runtime.Stable

@Stable
data class FormUiState(
    val text: String,
    val check: Boolean,
    val select: FavoriteSelect,
) {
    companion object {
        val Initial = FormUiState(
            text = "",
            check = false,
            select = FavoriteSelect.None,
        )
    }
}

enum class FavoriteSelect(val displayedName: String) {
    Compose("Compose Navigation"),
    View("Navigation Component with View"),
    None("neither")
}