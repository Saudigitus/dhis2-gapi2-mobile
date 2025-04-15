package org.saudigitus.gapi.presentation.screens.home

import androidx.compose.runtime.Immutable
import org.saudigitus.gapi.presentation.models.FilterType

@Immutable
sealed class HomeUiEvent {
    data class OnFilterChange<T>(val filterType: FilterType, val obj: T) : HomeUiEvent()
    data class OnTeiClick(val tei: String, val enrollment: String) : HomeUiEvent()
    data class NavTo(val route: String) : HomeUiEvent()
    data object OnBack : HomeUiEvent()
    data object HideShowFilter : HomeUiEvent()
    data object Sync : HomeUiEvent()
}
