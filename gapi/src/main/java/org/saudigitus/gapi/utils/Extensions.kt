package org.saudigitus.gapi.utils

import org.saudigitus.gapi.data.models.SearchTeiModel
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper

fun SearchTeiModel.map(
    teiCardMapper: TEICardMapper,
    onSyncIconClick: ((uid: String) -> Unit)? = null,
    onCardClick: (tei: String, enrollment: String) -> Unit = { _, _ -> },
) = teiCardMapper.map(
    searchTEIModel = this,
    onSyncIconClick = {
        if (onSyncIconClick != null) {
            onSyncIconClick(this.uid())
        }
    },
    onCardClick = {
        onCardClick(this.uid(), this.selectedEnrollment.uid() ?: "")
    },
    onImageClick = {},
)