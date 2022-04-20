package com.example.map.resource.map_resource_locator

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalContext
import com.example.map.resource.map_resource_locator.ui.theme.MapResourceLocatorTheme
import com.example.map.resource.map_resource_locator.screens.MainActivityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        super.onCreate(savedInstanceState)
        setContent {
            MapResourceLocatorTheme {
                Surface(color = MaterialTheme.colors.background) {
                    LockScreenOrientation(orientation = ActivityInfo.SCREEN_ORIENTATION_LOCKED)
                    MainActivityScreen()
                }
            }
        }
    }

    companion object {
        private lateinit var appContext: Context
        fun getContext(): Context = appContext
    }
}

/**
 * Used to block the landscape view in the LoginScreen
 */
@Composable
fun LockScreenOrientation(orientation: Int) {
    val context = LocalContext.current
    DisposableEffect(Unit) {
        val activity = context.findActivity() ?: return@DisposableEffect onDispose {}
        val originalOrientation = activity.requestedOrientation
        activity.requestedOrientation = orientation
        onDispose {
            // restore original orientation when view disappears
            activity.requestedOrientation = originalOrientation
        }
    }
}

private fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}