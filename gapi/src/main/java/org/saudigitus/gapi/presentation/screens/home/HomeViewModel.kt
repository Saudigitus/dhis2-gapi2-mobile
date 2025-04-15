package org.saudigitus.gapi.presentation.screens.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.saudigitus.gapi.data.local.ProgramRepository
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    savedStateHandle: SavedStateHandle,
    teiCardMapper: TEICardMapper,
    private val teiRepository: TeiRepository,
    private val programRepository: ProgramRepository,
) : ViewModel() {

    private val viewModelState = MutableStateFlow(
        HomeUiState(
            toolbarHeaders = ToolbarHeaders(
                title = "Home",
            ),
            teiCardMapper = teiCardMapper,
            program = savedStateHandle.get<String>("program") ?: "",
        ),
    )

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value,
        )

    val program = savedStateHandle.get<String>("program") ?: ""

    init {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(
                    projects = programRepository.projects(""),
                    teis = teiRepository.getBenefits("", program),
                )
            }
        }
    }



    fun onUIEvent(uiEvent: HomeUiEvent) = Unit

}
