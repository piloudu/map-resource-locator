package com.example.map.resource.map_resource_locator.view_model

import com.example.map.resource.map_resource_locator.get_data.Cache
import com.example.map.resource.map_resource_locator.get_data.CacheData
import com.example.map.resource.map_resource_locator.utils.toastMessage
import com.example.map.resource.map_resource_locator.utils.withViewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow

/**
 * MainViewModel is not made a singleton in order for tests to be able to instance a new object
 * for each case
 */
abstract class MainViewModel : BaseViewModel<MainActivityState, MainActivityUserIntent>() {
    private val reducer = MainReducer(MainActivityState.initial())

    override val state: StateFlow<MainActivityState> = reducer.state

    fun sendIntent(userIntent: MainActivityUserIntent) {
        withViewModelScope(Dispatchers.Default) {
            setStateCache()
            userIntent.action()
        }
        reducer.sendIntent(userIntent)
    }

    private fun setStateCache() {
        val oldCache = MainViewModelInstance.state.value.cache
        withViewModelScope {
            MainViewModelInstance.state.value.cache = Cache.get()
            if (oldCache != MainViewModelInstance.state.value.cache)
                toastMessage("Data cached")
        }.invokeOnCompletion { sendIntent(MainActivityUserIntent.Logged) }
    }

    private class MainReducer(initialState: MainActivityState) :
        Reducer<MainActivityState, MainActivityUserIntent>(initialState) {
        override fun reduce(oldState: MainActivityState, userIntent: MainActivityUserIntent) {
            when (userIntent) {
                is MainActivityUserIntent.Login -> setState(
                    oldState.copy(
                        innerState = AppState.LOADING
                    )
                )
                is MainActivityUserIntent.Logged -> setState(
                    oldState.copy(
                        innerState = AppState.MAIN
                    )
                )
                is MainActivityUserIntent.SelectMarker -> setState(
                    oldState.copy(
                        selectedMarker = userIntent.markerId
                    )
                )
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
    object Logged : MainActivityUserIntent()
    class SelectMarker(val markerId: String) : MainActivityUserIntent()
}

data class MainActivityState(
    val innerState: AppState,
    var cache: CacheData,
    val selectedMarker: String
) : UiState {
    companion object {
        fun initial() = MainActivityState(
            innerState = AppState.LOGIN,
            cache = CacheData(),
            selectedMarker = ""
        )
    }
}

enum class AppState {
    LOGIN, LOADING, MAIN
}