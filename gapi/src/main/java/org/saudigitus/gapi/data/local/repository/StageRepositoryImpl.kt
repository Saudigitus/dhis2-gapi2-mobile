package org.saudigitus.gapi.data.local.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.hisp.dhis.android.core.D2
import org.saudigitus.gapi.data.local.StageRepository
import org.saudigitus.gapi.data.models.Stage
import javax.inject.Inject

class StageRepositoryImpl
@Inject constructor(
    private val d2: D2,
): StageRepository {
    override suspend fun getStages(program: String) = withContext(Dispatchers.IO) {
        return@withContext d2.programModule().programStages()
            .byProgramUid().eq(program)
            .blockingGet()
            .map {
                Stage(
                    uid = it.uid(),
                    code = it.code() ?: "",
                    displayName = it.displayName() ?: ""
                )
            }
    }
}