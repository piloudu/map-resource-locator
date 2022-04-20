package com.example.map.resource.map_resource_locator.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.map.resource.map_resource_locator.screens.LoginScreen
import com.example.map.resource.map_resource_locator.screens.LoginScreenTags
import com.example.map.resource.map_resource_locator.ui.theme.MapResourceLocatorTheme
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.Test

@DisplayName("LoginScreen")
class LoginScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun withLoginScreenContentSetter(
        test: ComposeContentTestRule.() -> Unit
    ) {
        composeTestRule.setContent {
            MapResourceLocatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LoginScreen(modifier = Modifier.fillMaxSize())
                }
            }
        }
        composeTestRule.test()
    }

    @DisplayName("has welcome message")
    @Test
    fun login_screen_displays_the_welcome_message() {
        withLoginScreenContentSetter {
            onNodeWithTag(LoginScreenTags.GREETING_MESSAGE.name, true).assertIsDisplayed()
        }
    }

    @DisplayName("has emojis of the vehicles")
    @Test
    fun login_screen_displays_greeting_emoji() {
        withLoginScreenContentSetter {
            onNodeWithTag(LoginScreenTags.VEHICLES_EMOJIS.name, true).assertIsDisplayed()
        }
    }

    @DisplayName("has instructions message")
    @Test
    fun login_screen_displays_instructions_message() {
        withLoginScreenContentSetter {
            onNodeWithTag(LoginScreenTags.LOGIN_INSTRUCTION.name, true).assertIsDisplayed()
        }
    }

    @DisplayName("has arrows block")
    @Test
    fun login_screen_displays_arrows_block() {
        withLoginScreenContentSetter {
            onNodeWithTag(LoginScreenTags.ARROWS.name, true).assertIsDisplayed()
        }
    }
}