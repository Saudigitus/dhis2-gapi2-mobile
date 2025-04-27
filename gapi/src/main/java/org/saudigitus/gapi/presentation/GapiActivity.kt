package org.saudigitus.gapi.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.rememberNavController
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import org.dhis2.commons.sync.SyncContext
import org.dhis2.commons.sync.SyncDialog
import org.hisp.dhis.mobile.ui.designsystem.theme.DHIS2Theme
import org.saudigitus.gapi.R
import org.saudigitus.gapi.presentation.screens.dashboard.DashboardViewModel
import org.saudigitus.gapi.presentation.screens.form.FormScreen
import org.saudigitus.gapi.presentation.screens.form.FormViewModel
import org.saudigitus.gapi.presentation.screens.home.HomeRoute
import org.saudigitus.gapi.presentation.screens.home.HomeViewModel
import org.saudigitus.gapi.presentation.screens.nav.AppNavigator
import org.saudigitus.gapi.presentation.screens.theme.GapiAndroidTheme

@AndroidEntryPoint
class GapiActivity : FragmentActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private val formViewModel: FormViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val searchTeiModel by dashboardViewModel.searchTeiModel.collectAsStateWithLifecycle()
            val options by formViewModel.options.collectAsStateWithLifecycle()

            GapiAndroidTheme(
                dynamicColor = false,
                darkTheme = false,
            ) {
                /*HomeRoute(
                    viewModel = viewModel,
                    navController = navController,
                    navBack = { finish() },
                    sync = ::syncProgram,
                    onTeiClick = ::launchTeiDashboard,
                )*/
            }
        }
    }

    private fun syncProgram() {
        SyncDialog(
            activity = this@GapiActivity,
            recordUid = viewModel.program,
            syncContext = SyncContext.TrackerProgram(viewModel.program),
            onNoConnectionListener = {
                Snackbar.make(
                    this.window.decorView.rootView,
                    getString(R.string.sync_offline_check_connection),
                    Snackbar.LENGTH_SHORT,
                ).show()
            },
        ).show()
    }

    private fun launchTeiDashboard(
        tei: String,
        enrollment: String,
    ) {
        AppNavigator(
            activity = this@GapiActivity,
            tei = tei,
            program = "QutYMex7629",
            benefitEntity = "LYuP7aPXzKT",
            enrollment = enrollment,
        ).navigateToDashboard()
    }
}
