package org.saudigitus.gapi.presentation.screens.nav

import android.content.Context
import android.content.Intent

interface TeiDashboardComponentProvider {
    fun launch(
        context: Context,
        teiUid: String?,
        programUid: String?,
        benefitEntity: String?,
        enrollmentUid: String?,
    ): Intent
}
