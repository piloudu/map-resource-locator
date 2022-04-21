package com.example.map.resource.map_resource_locator.view_model

import androidx.lifecycle.viewModelScope
import com.example.map.resource.map_resource_locator.get_data.Cache
import com.example.map.resource.map_resource_locator.get_data.CacheData
import com.example.map.resource.map_resource_locator.utils.toastMessage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * MainViewModel is not made a singleton in order for tests to be able to instance a new object
 * for each case
 */
abstract class MainViewModel : BaseViewModel<MainActivityState, MainActivityUserIntent>() {
    private val reducer = MainReducer(MainActivityState.initial())

    override val state: StateFlow<MainActivityState> = reducer.state

    fun sendIntent(userIntent: MainActivityUserIntent) {
        viewModelScope.launch(Dispatchers.Default) {
            userIntent.setStateCache()
            userIntent.action()
        }
        reducer.sendIntent(userIntent)
    }

    private class MainReducer(initialState: MainActivityState) :
        Reducer<MainActivityState, MainActivityUserIntent>(initialState) {
        override fun reduce(oldState: MainActivityState, userIntent: MainActivityUserIntent) {
            when (userIntent) {
                is MainActivityUserIntent.Login -> setState(
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
    class SelectMarker(val markerId: String) : MainActivityUserIntent()

    suspend fun setStateCache() {
        val oldCache = MainViewModelInstance.state.value.cache
        MainViewModelInstance.state.value.cache = Cache.get()
        if (oldCache != MainViewModelInstance.state.value.cache)
            withContext(Dispatchers.Main) {
                toastMessage("Data cached")
            }
        println("Cache: " + MainViewModelInstance.state.value.cache)
    }
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
    LOGIN, MAIN
}