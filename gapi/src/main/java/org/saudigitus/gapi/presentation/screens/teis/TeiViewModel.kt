package org.saudigitus.gapi.presentation.screens.teis

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.saudigitus.gapi.data.local.ProgramRepository
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper
import javax.inject.Inject

@HiltViewModel
class TeiViewModel
@Inject constructor(
    private val teiRepository: TeiRepository,
    private val programRepository: ProgramRepository,
    private val teiCardMapper: TEICardMapper,
): ViewModel() {
    private val viewModelState = MutableStateFlow(
        TeiUiState(
            toolbarHeaders = ToolbarHeaders(
                title = "Benefits",
            ),
            teiCardMapper = teiCardMapper,
            program = "LYuP7aPXzKT"
        ),
    )

    val uiState = viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value,
        )

    init {
        viewModelScope.launch {
            viewModelState.update {
                it.copy(teis = teiRepository.getBenefits("rsqRHZcoQis", "LYuP7aPXzKT"))
            }
        }
    }

    fun onUIEvent(uiEvent: TeiUiEvent) = Unit
}