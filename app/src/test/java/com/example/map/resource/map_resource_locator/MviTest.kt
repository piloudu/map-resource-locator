package com.example.map.resource.map_resource_locator

import com.example.map.resource.map_resource_locator.view_model.AppState
import com.example.map.resource.map_resource_locator.view_model.MainActivityState
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
    fun `the inner state is set to MAIN on login`() {
        withTestScope {
            mainViewModelTestInstance.sendIntent(MainActivityUserIntent.Login)
            val state = mainViewModelTestInstance.state.value
            val expectedState = MainActivityState(
                innerState = AppState.MAIN,
                cache = mainViewModelTestInstance.state.value.cache
            )
            state shouldBe expectedState
        }
    }
}