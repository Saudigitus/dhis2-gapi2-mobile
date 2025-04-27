package org.saudigitus.gapi.presentation.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.saudigitus.gapi.R
import org.saudigitus.gapi.presentation.screens.theme.GapiAndroidTheme

@Composable
fun TextButton(
    title: String,
    containerColor: Color,
    enabled: Boolean = true,
    contentColor: Color,
    onClick: () -> Unit,
) {
    Button(
        onClick = { onClick.invoke() },
        border = BorderStroke(width = 0.dp, color = Color.White),
        enabled = enabled,
        shape = ShapeDefaults.Small,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = contentColor,
        ),
    ) {
        Text(text = title)
    }
}

@Composable
fun ActionButtons(
    modifier: Modifier = Modifier,
    contentColor: Color,
    disableActions: Boolean = false,
    onCancel: () -> Unit,
    onDone: () -> Unit,
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.End),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        TextButton(
            title = stringResource(R.string.cancel),
            containerColor = Color.White,
            enabled = !disableActions,
            contentColor = contentColor,
        ) { onCancel.invoke() }

        TextButton(
            title = stringResource(R.string.done),
            containerColor = Color.White,
            enabled = !disableActions,
            contentColor = contentColor,
        ) { onDone.invoke() }
    }
}

/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


@Composable
fun SelectButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val icon = if (selected) Icons.Filled.Done else Icons.Filled.Add
    val iconColor = if (selected) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.primary
    }
    val borderColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)
    }
    val backgroundColor = if (selected) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.onPrimary
    }
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        border = BorderStroke(1.dp, borderColor),
        modifier = modifier.size(36.dp, 36.dp)
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(iconColor),
            modifier = Modifier.padding(8.dp),
            contentDescription = null // toggleable at higher level
        )
    }
}

@Preview("Off")
@Preview("Off (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SelectTopicButtonPreviewOff() {
    SelectButtonPreviewTemplate(
        selected = false
    )
}

@Preview("On")
@Preview("On (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SelectButtonPreviewOn() {
    SelectButtonPreviewTemplate(
        selected = true
    )
}

@Composable
private fun SelectButtonPreviewTemplate(
    selected: Boolean
) {
    GapiAndroidTheme {
        Surface {
            SelectButton(
                modifier = Modifier.padding(32.dp),
                selected = selected
            )
        }
    }
}
