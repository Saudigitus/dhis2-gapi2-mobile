package org.saudigitus.gapi.data.local

import org.saudigitus.gapi.data.models.SearchTeiModel

interface TeiRepository {
    suspend fun getBenefits(
        orgUnit: String,
        project: String,
    ): List<SearchTeiModel>

    suspend fun getTei(program: String): List<SearchTeiModel>
}