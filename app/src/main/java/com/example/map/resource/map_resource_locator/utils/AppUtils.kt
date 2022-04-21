package com.example.map.resource.map_resource_locator.utils

import android.widget.Toast
import com.example.map.resource.map_resource_locator.MainActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng

const val APP_NAME = "Map Resource Locator"

const val defaultCameraZoom = 13f
val lisbonLatLng = LatLng(38.736946, -9.142685)
val initialCameraPosition = CameraPosition.fromLatLngZoom(lisbonLatLng, defaultCameraZoom)

val mapper: ObjectMapper =
    jacksonObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)

inline fun <reified T> String.deserialize(): List<T> = mapper.readValue(
    this,
    object : TypeReference<List<T>>() {}
)

fun toastMessage(message: String) {
    Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
}