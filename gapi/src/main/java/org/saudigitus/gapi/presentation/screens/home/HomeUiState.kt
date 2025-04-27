package org.saudigitus.gapi.presentation.screens.home

import android.os.Bundle
import androidx.compose.runtime.Stable
import org.saudigitus.gapi.data.models.OU
import org.saudigitus.gapi.data.models.Project
import org.saudigitus.gapi.data.models.SearchTeiModel
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper

@Stable
data class HomeUiState(
    val isLoading: Boolean = true,
    val displayFilters: Boolean = true,
    val teiCardMapper: TEICardMapper,
    val teis: List<SearchTeiModel> = emptyList(),
    val orgUnit: OU? = null,
    val projects: List<Project> = emptyList(),
    val program: String = "",
    val key: String? = null,
    val trackedEntityType: String = "",
    val toolbarHeaders: ToolbarHeaders = ToolbarHeaders(""),
) {

}
