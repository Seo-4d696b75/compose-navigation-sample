package jp.co.yumemi.compose_navigation_sample.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class FormViewModel @Inject constructor(

) : ViewModel() {

    private val _text = MutableStateFlow("")
    private val _check = MutableStateFlow(false)
    private val _select = MutableStateFlow(FavoriteSelect.None)

    val uiState = combine(
        _text,
        _check,
        _select,
    ) { text, check, select ->
        FormUiState(
            text = text,
            check = check,
            select = select,
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        FormUiState.Initial,
    )

    fun onTextChanged(text: String) {
        _text.update { text }
    }

    fun onCheckChanged(check: Boolean) {
        _check.update { check }
    }

    fun onSelectChanged(select: FavoriteSelect) {
        _select.update { select }
    }
}