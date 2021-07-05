package com.andariadar.stateflow

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _dayState = MutableStateFlow("Sunday")
    val dayState: StateFlow<String> = _dayState

    fun setDay(day: String) {
        _dayState.value = day
    }
}

