package org.saudigitus.gapi.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import org.dhis2.ui.theme.colorPrimary

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeItem(
    modifier: Modifier = Modifier,
    icon: Painter,
    title: String,
    label: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(16.dp),
                ambientColor = colorPrimary.copy(alpha = 0.6f),
                clip = false,
                spotColor = colorPrimary.copy(alpha = 0.8f),
            ),
        elevation = 3.dp,
        shape = RoundedCornerShape(16.dp),
        enabled = enabled,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    modifier = Modifier.size(64.dp),
                    painter = icon,
                    contentDescription = label,
                )

                Text(
                    text = title,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black.copy(.75f),
                    fontSize = MaterialTheme.typography.titleSmall.fontSize,
                    maxLines = 2,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Divider(
                modifier = Modifier.fillMaxWidth()
                    .padding(vertical = 2.dp),
                color = Color.LightGray.copy(.85f),
                thickness = .9.dp,
            )
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = label,
                    fontWeight = FontWeight.Bold,
                    color = colorPrimary,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                    maxLines = 2,
                    softWrap = true,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
