package com.example.map.resource.map_resource_locator.utils

import android.widget.Toast
import com.example.map.resource.map_resource_locator.MainActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val mapper: ObjectMapper = jacksonObjectMapper().enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
inline fun <reified T> String.deserialize(): List<T> = mapper.readValue(
    this,
    object : TypeReference<List<T>>() {}
)

fun toastMessage(message: String) {
    Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
}