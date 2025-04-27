package org.saudigitus.gapi.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.data.models.SearchTeiModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(
    private val teiRepository: TeiRepository,
) : ViewModel() {

    private val _searchTeiModel = MutableStateFlow<SearchTeiModel?>(null)
    val searchTeiModel: StateFlow<SearchTeiModel?> = _searchTeiModel

    init {
        viewModelScope.launch {
            _searchTeiModel.value = teiRepository.getTeiByName(
                "QutYMex7629",
                "ifYwOiBpGBc",
                "PEIXARIA NOVO HORIZONTE RIBAUE-SEDE"
            )
        }
    }
}