package com.example.map.resource.map_resource_locator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.map.resource.map_resource_locator.view_model.AppState.*
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance

val modifier = Modifier
    .fillMaxSize()
    .background(color = Color.White)

@Composable
fun MainActivityScreen(
) {
    val state by MainViewModelInstance.state.collectAsState()

    Box(
        modifier = modifier
    ) {
        when (state.innerState) {
            LOGIN -> LoginScreen(modifier)
            LOADING, MAIN -> MainScreen(modifier)
        }
    }
}