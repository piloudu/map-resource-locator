package com.example.map.resource.map_resource_locator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.map.resource.map_resource_locator.screens.MainScreen
import com.example.map.resource.map_resource_locator.screens.MainScreenMap
import com.example.map.resource.map_resource_locator.screens.MainScreenTags
import com.example.map.resource.map_resource_locator.ui.theme.MapResourceLocatorTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private fun withMainScreenContentSetter(
        test: ComposeContentTestRule.() -> Unit
    ) {
        composeTestRule.setContent {
            MapResourceLocatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    MainScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
        composeTestRule.test()
    }

    @DisplayName("MainScreen displays the TopBar")
    @Test
    fun main_screen_displays_the_topbar() {
        withMainScreenContentSetter {
            composeTestRule
                .onNodeWithTag(MainScreenTags.TOPBAR.name, true)
                .assertIsDisplayed()
        }
    }

    @DisplayName("MainScreen displays the MainScreenMap")
    @Test
    fun main_screen_displays_the_map() {
        withMainScreenContentSetter {
            composeTestRule
                .onNodeWithTag(MainScreenTags.MAP.name, true)
                .assertIsDisplayed()
        }
    }

    @DisplayName("The Map displays the expected marker")
    @Test
    fun map_displays_the_expected_marker() {
        val expectedMarkerId = "PT-LIS-A00218"
        val cameraPosition = CameraPosition.fromLatLngZoom(
            LatLng(38.736946, -9.142685),
            18f
        )
        composeTestRule.setContent {
            MainScreenMap(cameraPosition = cameraPosition)
        }
        composeTestRule.onNodeWithTag(expectedMarkerId).assertIsDisplayed()
    }
}