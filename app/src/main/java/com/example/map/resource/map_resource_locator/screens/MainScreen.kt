package com.example.map.resource.map_resource_locator.screens

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.map.resource.map_resource_locator.MainActivity
import com.example.map.resource.map_resource_locator.data_model.Resource
import com.example.map.resource.map_resource_locator.ui.theme.Purple700
import com.example.map.resource.map_resource_locator.utils.*
import com.example.map.resource.map_resource_locator.view_model.AppState.*
import com.example.map.resource.map_resource_locator.view_model.MainActivityState
import com.example.map.resource.map_resource_locator.view_model.MainActivityUserIntent
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
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
fun MainScreen(modifier: Modifier = Modifier) {
    val state by MainViewModelInstance.state.collectAsState()
    val selectedResource = state.selectedResource
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    Column(modifier.fillMaxSize()) {
        TopBar(
            modifier = Modifier
                .testTag(MainScreenTags.TOPBAR.name)
                .height(32.dp)
        )
        when (state.innerState) {
            LOADING -> LoadingIndicator()
            else -> MainScreenMap(
                modifier = Modifier.testTag(MainScreenTags.MAP.name),
                cameraPosition = initialCameraPosition,
                resources = state.cache.resources
            )
        }
    }
    Column(Modifier.fillMaxHeight()) {
        selectedResource?.let {
            Spacer(modifier = Modifier.height((screenHeight * 4) / 7))
            PopupInfo(
                resource = it
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
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
    modifier: Modifier = Modifier,
    cameraPosition: CameraPosition,
    resources: List<Resource>
) {
    val cameraPositionState = rememberCameraPositionState { position = cameraPosition }
    GoogleMap(
        modifier = modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        onMapClick = { MainViewModelInstance.sendIntent(MainActivityUserIntent.Dismiss) }
    ) {
        resources.takeIf { it.isNotEmpty() }?.forEach {
            if (it.hasLatLng()) {
                val position = LatLng(
                    it.yPosition!!,
                    it.xPosition!!
                )
                MapMarker(
                    context = MainActivity.getContext(),
                    position = position,
                    resource = it,
                    iconResId = it.iconResId(),
                    newCameraPosition = {
                        cameraPositionState.position = focusMarker(
                            cameraPositionState.position.zoom,
                            position
                        )
                        true
                    }
                )
            }
        }
    }
}

@Composable
fun MapMarker(
    context: Context,
    position: LatLng,
    resource: Resource,
    @DrawableRes iconResId: Int,
    newCameraPosition: () -> Boolean
) {
    val state: MainActivityState by MainViewModelInstance.state.collectAsState()
    val isSelected = state.selectedResource == resource
    Marker(
        state = MarkerState(
            position = position
        ),
        title = resource.name ?: "",
        icon = if (!isSelected) bitmapDescriptorFromVector(context, iconResId) else null,
        onClick = {
            MainViewModelInstance.sendIntent(MainActivityUserIntent.SelectMarker(resource))
            newCameraPosition()
        }
    )
}

fun focusMarker(
    currentCameraZoom: Float,
    position: LatLng
): CameraPosition {
    val newCameraZoom = when {
        currentCameraZoom < 13f -> 14f
        currentCameraZoom >= 14f -> currentCameraZoom
        else -> currentCameraZoom + 1
    }
    return CameraPosition.fromLatLngZoom(position, newCameraZoom)
}

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    color: Color = Purple700
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Column(
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(screenHeight / 3))
        CircularProgressIndicator(
            modifier = Modifier
                .width(screenWidth / 2)
                .height(screenHeight / 3),
            color = color
        )
        Spacer(Modifier.height(screenHeight / 3))
    }
}