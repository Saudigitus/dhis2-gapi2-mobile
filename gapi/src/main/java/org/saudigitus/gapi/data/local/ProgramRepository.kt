package org.saudigitus.gapi.data.local

import org.saudigitus.gapi.data.models.Project

interface ProgramRepository {
    suspend fun projects(
        program: String
    ): List<Project>
}