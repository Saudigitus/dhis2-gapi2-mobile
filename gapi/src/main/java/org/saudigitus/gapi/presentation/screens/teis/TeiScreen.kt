package org.saudigitus.gapi.presentation.screens.teis

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountTree
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.saudigitus.gapi.R
import org.saudigitus.gapi.presentation.components.DropDownOu
import org.saudigitus.gapi.presentation.components.TEIList
import org.saudigitus.gapi.presentation.components.Toolbar
import org.saudigitus.gapi.presentation.components.ToolbarActionState

@Composable
fun TeiScreen(
    viewModel: TeiViewModel,
    onSyncClick: () -> Unit,
    onBackClick: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TeiUI(uiState) {
        when(it) {
            is TeiUiEvent.OnSyncClick -> { onSyncClick() }
            is TeiUiEvent.OnBackClick -> { onBackClick() }
            else -> {
                viewModel.onUIEvent(it)
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TeiUI(
    uiState: TeiUiState,
    uiEvent: (TeiUiEvent) -> Unit,
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
                navigationAction = { uiEvent(TeiUiEvent.OnBackClick) },
                disableNavigation = false,
                actionState = ToolbarActionState(
                    syncVisibility = true,
                    showFavorite = false,
                ),
                syncAction = { uiEvent(TeiUiEvent.OnSyncClick) },
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
                        selectedOu = uiState.orgUnit,
                        program = uiState.program,
                        onItemClick = {
                            uiEvent(TeiUiEvent.OnOuChange(it))
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
                TEIList(
                    teiCardMapper = uiState.teiCardMapper,
                    teis = uiState.teis,
                    onCardClick = { tei, enrollment -> uiEvent(TeiUiEvent.OnTeiClick(tei, enrollment)) }
                )
            }
        }
    }
}
