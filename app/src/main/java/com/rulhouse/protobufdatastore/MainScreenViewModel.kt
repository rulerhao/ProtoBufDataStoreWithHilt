package com.rulhouse.protobufdatastore

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rulhouse.protobufdatastore.data.UserPreferencesRepository
import com.rulhouse.protobufdatastore.use_cases.UserPreferencesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val sharedPrefsUseCases: UserPreferencesUseCases
): ViewModel() {
    private val _showCompleted = mutableStateOf(false)
    val showCompleted: State<Boolean> = _showCompleted

    init {
        viewModelScope.launch {
            sharedPrefsUseCases.getUserPreferencesFlow().collectLatest {
                _showCompleted.value = it.showCompleted
            }
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.ToggleShowCompleted -> {
                viewModelScope.launch {
                    sharedPrefsUseCases.updateShowCompleted(!showCompleted.value)
                }
            }
        }
    }
}