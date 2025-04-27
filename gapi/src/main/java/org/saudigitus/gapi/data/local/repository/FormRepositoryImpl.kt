package org.saudigitus.gapi.data.local.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.event.Event
import org.saudigitus.gapi.data.local.FormRepository
import org.saudigitus.gapi.data.models.Option
import javax.inject.Inject

class FormRepositoryImpl
@Inject constructor(
    private val d2: D2
): FormRepository {
    override suspend fun getChoiceQuestions(
        programId: String
    ) = withContext(Dispatchers.IO) {
        d2.eventModule().events()
            .byOrganisationUnitUid().eq("LJX5GuypkKy")
            .byProgramUid().eq(programId)
            .withTrackedEntityDataValues()
            .blockingGet()
            .mapNotNull {
                eventTransform(it, "SVlMmmrsB1E")
            }.take(5)
    }

    override suspend fun save() {
        TODO("Not yet implemented")
    }

    private fun eventTransform(
        event: Event,
        dataElement: String,
    ): Option? {
        val dataValue = event.trackedEntityDataValues()?.find { it.dataElement() == dataElement }

        return if (dataValue != null) {
            Option(
                id = event.uid(),
                name = dataValue.value() ?: "",
            )
        } else {
            null
        }
    }

}