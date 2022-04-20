package com.example.map.resource.map_resource_locator.screens

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.map.resource.map_resource_locator.ui.theme.Purple200
import com.example.map.resource.map_resource_locator.utils.APP_NAME
import com.example.map.resource.map_resource_locator.view_model.MainActivityUserIntent
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance

enum class LoginScreenTags {
    LOGIN_SCREEN, GREETING_MESSAGE, VEHICLES_EMOJIS, LOGIN_INSTRUCTION, ARROWS
}

enum class LoginScreenMessages(val message: String) {
    GREETING("Welcome to $APP_NAME!"),
    INSTRUCTION("Click anywhere to start")
}

@Composable
fun LoginScreen(modifier: Modifier) {
    Box(modifier = modifier.getLoginScreenModifier()) {
        Column(
            modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(Modifier.height(72.dp))
            LoginScreenText(
                text = LoginScreenMessages.GREETING.message,
                modifier = Modifier
                    .testTag(LoginScreenTags.GREETING_MESSAGE.name)
                    .weight(2f)
            )
            Spacer(Modifier.height(32.dp))
            EmojiRow(modifier = modifier.weight(2f))
            Spacer(Modifier.height(32.dp))
            LoginScreenText(
                text = LoginScreenMessages.INSTRUCTION.message,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
                modifier = Modifier
                    .testTag(LoginScreenTags.LOGIN_INSTRUCTION.name)
                    .weight(2f)
            )
            Spacer(Modifier.height(16.dp))
            ArrowsBlock(
                modifier = Modifier
                    .testTag(LoginScreenTags.ARROWS.name)
                    .weight(5f)
            )
            Spacer(Modifier.height(72.dp))
        }
    }
}

val gradient = Brush.radialGradient(
    colors = listOf(Color.Blue, Purple200),
    center = Offset.Infinite,
    radius = 2700f
)

private fun Modifier.getLoginScreenModifier() = this
    .testTag(LoginScreenTags.LOGIN_SCREEN.name)
    .fillMaxSize()
    .background(
        brush = gradient
    )
    .clickable {
        MainViewModelInstance.sendIntent(MainActivityUserIntent.Login)
    }

@Composable
fun LoginScreenText(
    text: String,
    fontWeight: FontWeight = FontWeight.Bold,
    fontSize: TextUnit = 30.sp,
    modifier: Modifier
) {
    Text(
        textAlign = TextAlign.Center,
        color = Color.Black,
        fontSize = fontSize,
        text = text,
        fontFamily = FontFamily.Monospace,
        fontWeight = fontWeight,
        modifier = modifier
    )
}

@Composable
fun Emoji(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        fontSize = 40.sp,
        modifier = modifier
    )
}

@Composable
fun EmojiRow(
    modifier: Modifier
) {
    val emojiList = listOf("✈️", "🚇", "🚖", "🚍", "🛵")
    Row(
        modifier
            .fillMaxWidth()
            .testTag(LoginScreenTags.VEHICLES_EMOJIS.name),
        horizontalArrangement = Arrangement.Center,
    ) {
        emojiList.forEach {
            Emoji(it)
        }
    }
}

@Composable
fun ArrowsBlock(modifier: Modifier) {
    Box(modifier.width(160.dp)) {
        ArrowsShape()
    }
}

@Composable
fun ArrowsShape() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val modifier = Modifier.weight(1f)

        @Composable
        fun ArrowEmoji(text: String) = Emoji(
            text = text,
            modifier = modifier
        )

        Row(horizontalArrangement = Arrangement.Center) {

            ArrowEmoji(text = "↖️")
            ArrowEmoji(text = "⬆️")
            ArrowEmoji(text = "↗️")
        }
        Row(horizontalArrangement = Arrangement.Center) {
            ArrowEmoji(text = "⬅️")
            Spacer(modifier = Modifier.width(48.dp))
            ArrowEmoji(text = "➡️")
        }
        Row(horizontalArrangement = Arrangement.Center) {
            ArrowEmoji(text = "↙️")
            ArrowEmoji(text = "⬇️")
            ArrowEmoji(text = "↘️")
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4_XL)
@Composable
fun LoginScreenPreview() {
    LoginScreen(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight()
    )
}