package jp.co.yumemi.compose_navigation_sample.ui.screen.result

import jp.co.yumemi.compose_navigation_sample.ui.screen.form.FavoriteSelect
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed interface ResultArg {

    @Serializable
    @SerialName("a")
    data class A(
        val name: String,
        val isComposeMaster: Boolean,
    ) : ResultArg

    @Serializable
    @SerialName("b")
    data class B(
        val name: String,
        val favorite: FavoriteSelect,
    ) : ResultArg
}