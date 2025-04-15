package org.saudigitus.gapi.data.local.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.dhis2.commons.bindings.program
import org.hisp.dhis.android.core.D2
import org.saudigitus.gapi.data.local.ProgramRepository
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.data.models.Project
import javax.inject.Inject

class ProgramRepositoryImpl
@Inject constructor(
    private val d2: D2,
    private val teiRepository: TeiRepository,
) : ProgramRepository {
    override suspend fun projects(program: String) = withContext(Dispatchers.IO) {
        val programObj = d2.program(program)

        return@withContext if (programObj != null) {
            teiRepository.getTei(programObj.uid())
                .map {
                    val attrValues = it.attributeValues.values.toList()

                    Project(
                        title = attrValues.getOrNull(0)?.value() ?: "",
                        duration = attrValues.getOrNull(1)?.value() ?: "0",
                        financier = attrValues.getOrNull(2)?.value() ?: "",
                        implementer = attrValues.getOrNull(3)?.value() ?: "",
                        submittedApplication = attrValues.getOrNull(4)?.value() ?: "",
                        approvedApplication = attrValues.getOrNull(5)?.value() ?: "",
                    )
                }
        } else emptyList()
    }
}