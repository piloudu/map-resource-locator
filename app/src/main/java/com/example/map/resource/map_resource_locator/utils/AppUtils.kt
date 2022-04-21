package com.example.map.resource.map_resource_locator.utils

import android.content.Context
import android.graphics.Bitmap
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewModelScope
import com.example.map.resource.map_resource_locator.MainActivity
import com.example.map.resource.map_resource_locator.R
import com.example.map.resource.map_resource_locator.data_model.Resource
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val APP_NAME = "Map Resource Locator"
const val ZONE473 = 473
const val ZONE412 = 412

val mapper: ObjectMapper =
    jacksonObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)

inline fun <reified T> String.deserialize(): List<T> = mapper.readValue(
    this,
    object : TypeReference<List<T>>() {}
)

fun withViewModelScope(
    dispatcher: CoroutineDispatcher = Dispatchers.Main.immediate,
    execute: suspend CoroutineScope.() -> Unit
) = MainViewModelInstance.viewModelScope.launch(dispatcher) { execute() }

fun toastMessage(message: String) {
    Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
}

const val defaultCameraZoom = 13f
val lisbonLatLng = LatLng(38.736946, -9.142685)
val initialCameraPosition = CameraPosition.fromLatLngZoom(lisbonLatLng, defaultCameraZoom)

fun Resource.hasLatLng() = xPosition != null && yPosition != null
fun Resource.iconResId() = when (companyZoneId) {
    ZONE412 -> R.drawable.ic_bike_marker
    ZONE473 -> R.drawable.ic_car_marker
    else -> throw Exception("companyZoneId is $companyZoneId")
}

fun bitmapDescriptorFromVector(
    context: Context,
    vectorResId: Int
): BitmapDescriptor? {

    // get drawable from resources and create e Bitmap for it
    val drawable = ContextCompat.getDrawable(context, vectorResId) ?: return null
    drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
    val bm = Bitmap.createBitmap(
        drawable.intrinsicWidth,
        drawable.intrinsicHeight,
        Bitmap.Config.ARGB_8888
    )

    // create a BitmapDescriptor with it
    val canvas = android.graphics.Canvas(bm)
    drawable.draw(canvas)
    return BitmapDescriptorFactory.fromBitmap(bm)
}

val mockResource = Resource(
    companyZoneId = 473,
    range = 3,
    batteryLevel = 6,
    helmets = 2,
    availableResources = 0,
    spacesAvailable = 0,
    bikesAvailable = 2,
    id = "mockId",
    name = "mockName",
    licencePlate = "mockLicensePlate",
    resourceType = "mockResourceType",
    model = "mockModel",
    resourceImageId = "mockResImg",
    resourcesImagesUrls = listOf("mockResUrl"),
    allowDropOff = true,
    realTimeData = true,
    station = false
)