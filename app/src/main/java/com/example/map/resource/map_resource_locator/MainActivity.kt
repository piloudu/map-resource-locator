package com.example.map.resource.map_resource_locator

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import com.example.map.resource.map_resource_locator.ui.theme.MapResourceLocator
import com.example.map.resource.map_resource_locator.screens.MainActivityScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        appContext = applicationContext
        super.onCreate(savedInstanceState)
        setContent {
            MapResourceLocator() {
                Surface(color = MaterialTheme.colors.background) {
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