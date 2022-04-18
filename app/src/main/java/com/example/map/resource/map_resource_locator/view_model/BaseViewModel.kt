package com.example.map.resource.map_resource_locator.view_model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<out S : UiState, in I : UserIntent> : ViewModel() {

    abstract val state: Flow<S>

}