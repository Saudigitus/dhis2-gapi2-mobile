package org.saudigitus.gapi.presentation.screens.form

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.dhis2.ui.theme.colorPrimary
import org.saudigitus.gapi.R
import org.saudigitus.gapi.presentation.components.Toolbar
import org.saudigitus.gapi.presentation.components.ToolbarActionState
import org.saudigitus.gapi.presentation.components.ToolbarHeaders
import org.saudigitus.gapi.presentation.screens.form.components.SingleChoiceQuestionsWrapper
import org.saudigitus.gapi.utils.MockData


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen() {
    Scaffold(
        topBar = {
            Toolbar(
                headers = ToolbarHeaders(title = "Monitoria"),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF2C98F0),
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White,
                ),
                navigationAction = { /*onEvent(HomeUiEvent.OnBack)*/ },
                disableNavigation = false,
                actionState = ToolbarActionState(
                    syncVisibility = true,
                    filterVisibility = false,
                ),
                syncAction = { /*onEvent(HomeUiEvent.Sync)*/ },
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(stringResource(R.string.save))
                },
                icon = {
                    Icon(
                        imageVector = Icons.Default.Save,
                        contentDescription = stringResource(R.string.save)
                    )
                },
                onClick = { /*TODO*/ },
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorPrimary)
                .padding(innerPadding),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
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
                SingleChoiceQuestionsWrapper(
                    modifier = Modifier.fillMaxSize(),
                    questions = MockData.singleChoiceQuestions,
                )
            }
        }
    }
}