package org.saudigitus.gapi.presentation.screens.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.saudigitus.gapi.data.local.FormRepository
import org.saudigitus.gapi.data.models.Option
import org.saudigitus.gapi.utils.MockData
import javax.inject.Inject

@HiltViewModel
class FormViewModel
@Inject constructor(
    private val formRepository: FormRepository
) : ViewModel() {
    private val _options = MutableStateFlow<List<Option>>(emptyList())
    val options: StateFlow<List<Option>> = _options

    init {
        viewModelScope.launch {
            //_options.value = MockData.monitoringIssues.second
        }
    }
}