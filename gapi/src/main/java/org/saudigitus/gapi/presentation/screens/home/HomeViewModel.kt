package org.saudigitus.gapi.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        HomeUiState(
            toolbarHeaders = ToolbarHeaders(
                title = "Home",
            ),
        ),
    )

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value,
        )

    private val _program = MutableStateFlow("")
    val program: StateFlow<String> = _program

    init {

    }

    fun initProgram(program: String) {
        _program.value = program
    }

    fun onUIEvent(uiEvent: HomeUiEvent) = Unit

}
