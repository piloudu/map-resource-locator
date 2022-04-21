package com.example.map.resource.map_resource_locator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.map.resource.map_resource_locator.screens.MainScreen
import com.example.map.resource.map_resource_locator.screens.MainScreenTags
import com.example.map.resource.map_resource_locator.ui.theme.MapResourceLocatorTheme
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.DisplayName

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
}