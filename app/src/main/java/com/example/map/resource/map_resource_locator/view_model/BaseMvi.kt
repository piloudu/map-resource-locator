package com.example.map.resource.map_resource_locator.view_model

import kotlinx.coroutines.flow.MutableStateFlow

abstract class Reducer<S : UiState, in I : UserIntent>(initialState: S) {

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state

    fun sendIntent(intent: I) {
        reduce(_state.value, intent)
    }

    fun setState(newState: S) {
        _state.tryEmit(newState)
    }

    abstract fun reduce(oldState: S, userIntent: I)
}

interface UserIntent {
    suspend fun action(): String = "Empty action"
}

interface UiState