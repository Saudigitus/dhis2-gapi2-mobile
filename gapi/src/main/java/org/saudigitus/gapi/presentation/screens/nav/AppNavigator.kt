package org.saudigitus.gapi.presentation.screens.nav

import androidx.fragment.app.FragmentActivity

class AppNavigator(
    val activity: FragmentActivity,
    val tei: String,
    val program: String,
    val benefitEntity: String,
    val enrollment: String,
) {
    fun navigateToDashboard() {
        (activity.applicationContext as? NavigatorComponentProvider)
            ?.dashboard
            ?.launch(
                activity,
                tei,
                program,
                benefitEntity,
                enrollment,
            )
            ?.let {
                activity.startActivity(it)
            }
    }
}
