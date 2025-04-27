package org.saudigitus.gapi.presentation.screens.form.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.saudigitus.gapi.data.models.Option

@Composable
fun SingleChoiceQuestionsWrapper(
    modifier: Modifier = Modifier,
    questions: List<Pair<String, List<Option>>>
) {
    LazyColumn(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
        contentPadding = PaddingValues(bottom = 108.dp),
    ) {
        items(questions) { question ->
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = question.first,
                    fontWeight = FontWeight.Bold,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize,
                    modifier = Modifier.fillMaxWidth()
                        .padding(16.dp)
                )
                HorizontalDivider()
                SingleChoice(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    options = question.second,
                    selectedOptionId = null,
                    onAnswerSelected = { _ ->
                        // Handle the selected option
                        //selectedOptionId = selectedOption.id
                    }
                )
            }
        }
    }
}