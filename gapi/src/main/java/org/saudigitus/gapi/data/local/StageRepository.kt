package org.saudigitus.gapi.data.local

import org.saudigitus.gapi.data.models.Stage

interface StageRepository {
    suspend fun getStages(program: String): List<Stage>
}