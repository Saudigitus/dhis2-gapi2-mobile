package org.saudigitus.gapi.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.dhis2.commons.Constants

import org.saudigitus.gapi.R
import org.saudigitus.gapi.presentation.components.DropDownOu
import org.saudigitus.gapi.presentation.components.Toolbar
import org.saudigitus.gapi.presentation.components.ToolbarActionState
import org.saudigitus.gapi.presentation.models.FilterType

@Composable
fun HomeRoute(
    isExpandedScreen: Boolean,
    viewModel: HomeViewModel,
    navController: NavHostController,
    navBack: () -> Unit,
    sync: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeUI(isExpandedScreen, uiState = uiState) {
        when (it) {
            is HomeUiEvent.OnBack -> navBack()
            is HomeUiEvent.NavTo -> navController.navigate(it.route)
            is HomeUiEvent.Sync -> sync()
            else -> {
                viewModel.onUIEvent(it)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUI(
    isExpandedScreen: Boolean,
    uiState: HomeUiState,
    onEvent: (HomeUiEvent) -> Unit,
) {
    Scaffold(
        topBar = {
            Toolbar(
                headers = uiState.toolbarHeaders,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2C98F0),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                navigationAction = { onEvent(HomeUiEvent.OnBack) },
                disableNavigation = false,
                actionState = ToolbarActionState(
                    syncVisibility = true,
                    showFavorite = true,
                ),
                filterAction = {
                    onEvent(HomeUiEvent.HideShowFilter)
                },
                syncAction = { onEvent(HomeUiEvent.Sync) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFF2C98F0))
                .padding(paddingValues),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            AnimatedVisibility(visible = uiState.displayFilters) {
                Column(
                    modifier = Modifier.padding(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.Start,
                ) {
                    DropDownOu(
                        placeholder = stringResource(R.string.ou),
                        leadingIcon = Icons.Default.AccountTree,
                        selectedSchool = uiState.orgUnit,
                        program = uiState.program,
                        onItemClick = {
                            onEvent(HomeUiEvent.OnFilterChange(FilterType.OU, it))
                        },
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = Color.White,
                        shape = MaterialTheme.shapes.medium
                            .copy(
                                topStart = CornerSize(16.dp),
                                topEnd = CornerSize(16.dp),
                                bottomStart = CornerSize(0.dp),
                                bottomEnd = CornerSize(0.dp),
                            ),
                    ),
                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
               // TODO: Add selected filter info
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(vertical = 10.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        HomeItem(
                            modifier = Modifier.fillMaxWidth(),
                            icon = painterResource(R.drawable.ic_project),
                            title = "20 Project",
                            label = stringResource(R.string.calendar),
                            enabled = true,
                            onClick = {

                            },
                        )
                    }
                }
            }
        }
    }
}
