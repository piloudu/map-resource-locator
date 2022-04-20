package com.example.map.resource.map_resource_locator.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.CameraPositionState

enum class MainScreenTags {
    TOPBAR, MAP
}

@Composable
fun MainScreen(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
    }
}

@Composable
fun TopBar() {
    TODO("Not yet implemented")
}

@Composable
fun MainScreenMap(
    cameraPosition: CameraPosition,
    modifier: Modifier = Modifier
) {
    TODO("Not yet implemented")
}
