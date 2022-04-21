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
        cameraPositionState = cameraPositionState
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
                        cameraPositionState.position = focusMarker(position)
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
    val isSelected = state.selectedMarker == resource.id
    Marker(
        state = MarkerState(
            position = position
        ),
        title = resource.name ?: "",
        icon = if (!isSelected) bitmapDescriptorFromVector(context, iconResId) else null,
        onClick = {
            resource.id?.let {
                MainViewModelInstance.sendIntent(MainActivityUserIntent.SelectMarker(it))
            }
            newCameraPosition()
        }
    )
}

fun focusMarker(position: LatLng): CameraPosition = CameraPosition.fromLatLngZoom(position, 18f)

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    size: Int = 80,
    color: Color = Purple700
) {
    CircularProgressIndicator(
        modifier = Modifier
            .height(size.dp)
            .width(size.dp),
        color = color
    )
}