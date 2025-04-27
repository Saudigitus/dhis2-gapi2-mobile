package org.saudigitus.gapi.presentation.screens.form.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.saudigitus.gapi.data.models.Option


@Composable
fun SingleChoice(
    modifier: Modifier = Modifier,
    options: List<Option>,
    selectedOptionId: String? = null,
    onAnswerSelected: (Option) -> Unit,
) {
    val (selectedOptionIdState, onOptionSelected) = remember(selectedOptionId) {
        mutableStateOf(selectedOptionId)
    }

    Column(modifier = modifier) {
        options.forEach { option ->
            val isSelected = option.id == selectedOptionIdState

            val onClickHandle = {
                onOptionSelected(option.id)
                onAnswerSelected(option)
            }

            val borderColor = if (isSelected) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            } else {
                MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
            }
            val backgroundColor = if (isSelected) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.12f)
            } else {
                MaterialTheme.colorScheme.background
            }

            Surface(
                shape = MaterialTheme.shapes.small,
                border = BorderStroke(1.dp, borderColor),
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = isSelected,
                            onClick = onClickHandle
                        )
                        .background(backgroundColor)
                        .padding(vertical = 16.dp, horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = option.name,
                        softWrap = true,
                        modifier = Modifier.weight(1f)
                    )

                    RadioButton(
                        selected = isSelected,
                        onClick = onClickHandle,
                        colors = RadioButtonDefaults.colors(
                            selectedColor = MaterialTheme.colorScheme.primary
                        )
                    )
                }
            }
        }
    }
}

