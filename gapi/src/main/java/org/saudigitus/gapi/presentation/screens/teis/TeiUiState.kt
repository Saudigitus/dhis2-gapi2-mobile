package org.saudigitus.gapi.presentation.screens.teis

import org.saudigitus.gapi.data.models.OU
import org.saudigitus.gapi.data.models.SearchTeiModel
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper

data class TeiUiState(
    val toolbarHeaders: ToolbarHeaders = ToolbarHeaders(""),
    val displayFilters: Boolean = true,
    val teiCardMapper: TEICardMapper,
    val teis: List<SearchTeiModel> = emptyList(),
    val program: String,
    val orgUnit: OU? = null,
)
