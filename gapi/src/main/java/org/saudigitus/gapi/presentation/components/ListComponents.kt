package org.saudigitus.gapi.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.hisp.dhis.mobile.ui.designsystem.component.ListCard
import org.hisp.dhis.mobile.ui.designsystem.component.ListCardTitleModel
import org.saudigitus.gapi.R
import org.saudigitus.gapi.data.models.SearchTeiModel
import org.saudigitus.gapi.presentation.screens.teis.mapper.TEICardMapper
import org.saudigitus.gapi.utils.map

@Suppress("DEPRECATION")
@Composable
fun TEIList(
    label: String? = null,
    teiCardMapper: TEICardMapper,
    teis: List<SearchTeiModel>,
    onCardClick: (String, String) -> Unit = { _, _ -> },
) {
    label?.let {
        Text(
            text = it,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black.copy(.5f),
            fontFamily = FontFamily(Font(R.font.rubik_regular)),
        )
    }

    if (teis.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.Top),
        ) {
            items(teis) { project ->
                val card = project.map(teiCardMapper, onCardClick = onCardClick)

                ListCard(
                    modifier = Modifier.testTag("TEI_ITEM"),
                    listAvatar = card.avatar,
                    title = ListCardTitleModel(text = card.title),
                    lastUpdated = card.lastUpdated,
                    additionalInfoList = card.additionalInfo,
                    actionButton = card.actionButton,
                    expandLabelText = card.expandLabelText,
                    shrinkLabelText = card.shrinkLabelText,
                    onCardClick = card.onCardCLick,
                )
            }
        }
    } else {
        NoResults("Projects not found")
    }
}