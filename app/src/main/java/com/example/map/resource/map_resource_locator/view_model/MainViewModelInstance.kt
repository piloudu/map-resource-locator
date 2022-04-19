package com.example.map.resource.map_resource_locator.view_model

import androidx.lifecycle.viewModelScope
import com.example.map.resource.map_resource_locator.get_data.Cache
import com.example.map.resource.map_resource_locator.get_data.CacheData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * MainViewModel is not made a singleton in order for tests to be able to instance a new object
 * for each case
 */
abstract class MainViewModel : BaseViewModel<MainActivityState, MainActivityUserIntent>() {
    private val reducer = MainReducer(MainActivityState.initial())

    override val state: StateFlow<MainActivityState> = reducer.state

    fun sendIntent(userIntent: MainActivityUserIntent) {
        viewModelScope.launch(Dispatchers.Main) {
            userIntent.setStateCache()
            userIntent.action()
        }
        reducer.sendIntent(userIntent)
    }

    private class MainReducer(initialState: MainActivityState) :
        Reducer<MainActivityState, MainActivityUserIntent>(initialState) {
        override fun reduce(oldState: MainActivityState, userIntent: MainActivityUserIntent) {
            when (userIntent) {
                is MainActivityUserIntent.Login -> {
                    setState(
                        oldState.copy(
                            innerState = AppState.MAIN
                        )
                    )
                }
            }
        }
    }
}

/**
 * Create a MainViewModel instance to be used in the whole app
 */
object MainViewModelInstance : MainViewModel()

sealed class MainActivityUserIntent : UserIntent {
    object Login : MainActivityUserIntent()

    suspend fun setStateCache() {
        MainViewModelInstance.state.value.cache = Cache.get()
    }
}

data class MainActivityState(
    val innerState: AppState,
    var cache: CacheData
) : UiState {
    companion object {
        fun initial() = MainActivityState(
            innerState = AppState.LOGIN,
            cache = CacheData()
        )
    }
}

enum class AppState {
    LOGIN, MAIN
}