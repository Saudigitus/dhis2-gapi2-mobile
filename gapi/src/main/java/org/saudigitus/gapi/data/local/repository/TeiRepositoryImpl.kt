package org.saudigitus.gapi.data.local.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.dhis2.bindings.userFriendlyValue
import org.dhis2.commons.network.NetworkUtils
import org.hisp.dhis.android.core.D2
import org.hisp.dhis.android.core.arch.repositories.scope.RepositoryScope
import org.hisp.dhis.android.core.enrollment.EnrollmentStatus
import org.hisp.dhis.android.core.trackedentity.TrackedEntityAttribute
import org.hisp.dhis.android.core.trackedentity.TrackedEntityAttributeValue
import org.hisp.dhis.android.core.trackedentity.TrackedEntityInstance
import org.saudigitus.gapi.data.local.TeiRepository
import org.saudigitus.gapi.data.models.SearchTeiModel
import javax.inject.Inject

class TeiRepositoryImpl
@Inject constructor(
    private val d2: D2,
    private val networkUtils: NetworkUtils,
) : TeiRepository {

    private lateinit var currentProgram: String
    private val orgUnitNameCache = mutableMapOf<String, String?>()

    override suspend fun getBenefits(orgUnit: String, project: String) = withContext(Dispatchers.IO) {
        val repository = d2.trackedEntityModule().trackedEntityInstanceQuery()

        return@withContext if (networkUtils.isOnline()) {
            repository.offlineFirst().allowOnlineCache().eq(true)
                .byOrgUnits().eq(orgUnit)
                .byProgram().eq(project)
                .blockingGet()
                .flatMap { tei -> listOf(tei) }
                .map { tei -> transform(tei, project) }
        } else {
            repository.offlineOnly().allowOnlineCache().eq(false)
                .byOrgUnits().eq(orgUnit)
                .byProgram().eq(project)
                .blockingGet()
                .flatMap { tei -> listOf(tei) }
                .map { tei -> transform(tei, project) }
        }
    }

    override suspend fun getTei(program: String) = withContext(Dispatchers.IO) {
        val repository = d2.trackedEntityModule().trackedEntityInstanceQuery()

        return@withContext if (networkUtils.isOnline()) {
            repository.offlineFirst().allowOnlineCache().eq(true)
                .byProgram().eq(program)
                .blockingGet()
                .flatMap { tei -> listOf(tei) }
                .map { tei -> transform(tei, program) }
        } else {
            repository.offlineOnly().allowOnlineCache().eq(false)
                .byProgram().eq(program)
                .blockingGet()
                .flatMap { tei -> listOf(tei) }
                .map { tei -> transform(tei, program) }
        }
    }

    override suspend fun getTeiByName(
        program: String,
        attributeId: String,
        name: String
    ) = withContext(Dispatchers.IO) {
        val repository = d2.trackedEntityModule().trackedEntityInstanceQuery()

        return@withContext if (networkUtils.isOnline()) {
            val tei = repository.onlineFirst().allowOnlineCache().eq(true)
                .byProgram().eq(program)
                .byFilter(attributeId).eq(name)
                .one()
                .blockingGet()

            transform(tei, program)
        } else {
            val tei = repository.offlineOnly().allowOnlineCache().eq(false)
                .byProgram().eq(program)
                .byFilter(attributeId).eq(name)
                .one()
                .blockingGet()

            transform(tei, program)
        }
    }

    fun transform(
        tei: TrackedEntityInstance?,
        program: String?,
    ): SearchTeiModel {
        val searchTei = SearchTeiModel()
        searchTei.tei = tei
        currentProgram = program ?: ""

        if (tei?.trackedEntityAttributeValues() != null) {
            if (program != null) {
                val programAttributes = d2.programModule().programTrackedEntityAttributes()
                    .byProgram().eq(program)
                    .byDisplayInList().isTrue
                    .orderBySortOrder(RepositoryScope.OrderByDirection.ASC)
                    .blockingGet()

                for (programAttribute in programAttributes) {
                    val attribute = d2.trackedEntityModule().trackedEntityAttributes()
                        .uid(programAttribute.trackedEntityAttribute()!!.uid())
                        .blockingGet()

                    for (attrValue in tei.trackedEntityAttributeValues()!!) {
                        if (attrValue.trackedEntityAttribute() == attribute?.uid()) {
                            addAttribute(searchTei, attrValue, attribute)
                            break
                        }
                    }
                }
            } else {
                val typeAttributes = d2.trackedEntityModule().trackedEntityTypeAttributes()
                    .byTrackedEntityTypeUid().eq(searchTei.tei.trackedEntityType())
                    .byDisplayInList().isTrue
                    .blockingGet()
                for (typeAttribute in typeAttributes) {
                    val attribute = d2.trackedEntityModule().trackedEntityAttributes()
                        .uid(typeAttribute.trackedEntityAttribute()!!.uid())
                        .blockingGet()
                    for (attrValue in tei.trackedEntityAttributeValues()!!) {
                        if (attrValue.trackedEntityAttribute() == attribute?.uid()) {
                            addAttribute(searchTei, attrValue, attribute)
                            break
                        }
                    }
                }
            }

            val enrollments = d2.enrollmentModule().enrollments()
                .byTrackedEntityInstance().eq(tei.uid())
                .byProgram().eq("$program")
                .orderByEnrollmentDate(RepositoryScope.OrderByDirection.DESC)
                .blockingGet()

            if (enrollments.isNotEmpty()) {
                for (enrollment in enrollments) {
                    if (enrollment.status() == EnrollmentStatus.ACTIVE) {
                        searchTei.setCurrentEnrollment(enrollment)
                        break
                    }
                }
            }

            if (searchTei.selectedEnrollment == null) {
                searchTei.setCurrentEnrollment(enrollments[0])
            }

            for (enrollment in enrollments) {
                searchTei.addEnrollment(enrollment)
            }

            if (searchTei.selectedEnrollment != null) {
                searchTei.enrolledOrgUnit =
                    orgUnitName(searchTei.selectedEnrollment.organisationUnit()!!)
            } else {
                searchTei.enrolledOrgUnit = orgUnitName(searchTei.tei.organisationUnit()!!)
            }
        }

        searchTei.displayOrgUnit = displayOrgUnit()
        return searchTei
    }

    private fun addAttribute(
        searchTei: SearchTeiModel,
        attrValue: TrackedEntityAttributeValue,
        attribute: TrackedEntityAttribute?,
    ) {
        val friendlyValue = attrValue.userFriendlyValue(d2)

        val attrValueBuilder = TrackedEntityAttributeValue.builder()
        attrValueBuilder.value(friendlyValue)
            .created(attrValue.created())
            .lastUpdated(attrValue.lastUpdated())
            .trackedEntityAttribute(attrValue.trackedEntityAttribute())
            .trackedEntityInstance(searchTei.tei.uid())
        searchTei.addAttributeValue(attribute?.displayFormName(), attrValueBuilder.build())
    }

    private fun orgUnitName(orgUnitUid: String): String? {
        if (!orgUnitNameCache.containsKey(orgUnitUid)) {
            val organisationUnit = d2.organisationUnitModule()
                .organisationUnits()
                .uid(orgUnitUid)
                .blockingGet()
            orgUnitNameCache[orgUnitUid] = organisationUnit!!.displayName()
        }
        return orgUnitNameCache[orgUnitUid]
    }

    private fun displayOrgUnit(): Boolean {
        return d2.organisationUnitModule().organisationUnits()
            .byProgramUids(listOf(currentProgram))
            .blockingGet().size > 1
    }
}