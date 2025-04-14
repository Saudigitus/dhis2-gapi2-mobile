package org.saudigitus.gapi.presentation.screens.home

import android.os.Bundle
import androidx.compose.runtime.Stable
import org.saudigitus.gapi.data.models.OU
import org.saudigitus.gapi.presentation.components.DropdownItem
import org.saudigitus.gapi.presentation.components.ToolbarHeaders

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
    val displayFilters: Boolean = true,
    val orgUnit: OU? = null,
    val program: String = "",
    val key: String? = null,
    val trackedEntityType: String = "",
    val toolbarHeaders: ToolbarHeaders = ToolbarHeaders(""),
) {

}
