package org.saudigitus.gapi.data.local

import org.saudigitus.gapi.data.models.Option

interface FormRepository {
    suspend fun getChoiceQuestions(programId: String): List<Option>
    suspend fun save()
}