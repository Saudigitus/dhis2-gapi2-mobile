package org.saudigitus.gapi.presentation.screens.teis

import org.saudigitus.gapi.data.models.OU

sealed class TeiUiEvent {
    data object OnSyncClick: TeiUiEvent()
    data object OnBackClick: TeiUiEvent()
    data class OnTeiClick(val tei: String, val enrollment: String): TeiUiEvent()
    data class OnOuChange(val ou: OU): TeiUiEvent()
}