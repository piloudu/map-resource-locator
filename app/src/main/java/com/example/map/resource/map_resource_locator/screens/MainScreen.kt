package com.example.map.resource.map_resource_locator.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.map.resource.map_resource_locator.ui.theme.Purple700
import com.example.map.resource.map_resource_locator.utils.APP_NAME
import com.example.map.resource.map_resource_locator.utils.initialCameraPosition
import com.example.map.resource.map_resource_locator.utils.lisbonLatLng
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

enum class MainScreenTags {
    TOPBAR, MAP
}

enum class MainScreenMessages(val message: String) {
    HEADER(APP_NAME)
}

@Composable
fun MainScreen(modifier: Modifier) {
    Column(modifier.fillMaxSize()) {
        TopBar(
            modifier = Modifier
                .testTag(MainScreenTags.TOPBAR.name)
                .height(32.dp)
        )
        MainScreenMap(cameraPosition = initialCameraPosition)
    }
}

@Composable
fun TopBar(modifier: Modifier) {
    Box(
        modifier = modifier
            .background(Purple700)
            .fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = MainScreenMessages.HEADER.message,
            fontSize = 20.sp,
            color = White
        )
    }
}

@Composable
fun MainScreenMap(
    cameraPosition: CameraPosition,
    modifier: Modifier = Modifier
) {
    val cameraPositionState = rememberCameraPositionState { position = cameraPosition }
    val appState = MainViewModelInstance.state.collectAsState()
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        if (appState.value.cache.resources.isNotEmpty())
            Marker(
                state = MarkerState(position = lisbonLatLng),
                title = "Lisbon",
                snippet = "Marker in Lisbon"
            )
    }
}
