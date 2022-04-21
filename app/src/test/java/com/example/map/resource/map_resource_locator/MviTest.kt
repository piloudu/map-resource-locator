package com.example.map.resource.map_resource_locator

import com.example.map.resource.map_resource_locator.utils.mockResource
import com.example.map.resource.map_resource_locator.view_model.AppState.*
import com.example.map.resource.map_resource_locator.view_model.MainActivityUserIntent
import com.example.map.resource.map_resource_locator.view_model.MainViewModel
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("The MVI machine sets the proper state")
class MviTest {
    private lateinit var mainViewModelTestInstance: MainViewModel

    @BeforeEach
    fun `init view model`() {
        mainViewModelTestInstance = object : MainViewModel() {}
    }

    @DisplayName("after login")
    @Test
    fun `the inner state is set to LOADING while downloading the data`() {
        withTestScope {
            mainViewModelTestInstance.sendIntent(MainActivityUserIntent.Login)
            val state = mainViewModelTestInstance.state.value
            val expectedState = state.copy(innerState = LOADING)
            state shouldBe expectedState
        }
    }

    @DisplayName("on login completed")
    @Test
    fun `the inner state is set to MAIN after downloading the data`() {
        withTestScope {
            mainViewModelTestInstance.sendIntent(MainActivityUserIntent.Logged)
            val state = mainViewModelTestInstance.state.value
            val expectedState = state.copy(innerState = MAIN)
            state shouldBe expectedState
        }
    }

    @DisplayName("when selecting a marker in the MainScreen")
    @Test
    fun `the inner state is changed properly when selecting a marker in map`() {
        withTestScope {
            mainViewModelTestInstance.sendIntent(MainActivityUserIntent.SelectMarker(mockResource))
            val state = mainViewModelTestInstance.state.value
            val expectedState = state.copy(selectedResource = mockResource)
            state shouldBe expectedState
        }
    }
}