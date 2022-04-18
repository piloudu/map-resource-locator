package com.example.map.resource.map_resource_locator.utils

import android.widget.Toast
import com.example.map.resource.map_resource_locator.MainActivity
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

val mapper = jacksonObjectMapper()
inline fun <reified T> String.deserialize(): List<T> = mapper.readValue(
    this,
    object : TypeReference<List<T>>() {}
)

fun toastMessage(message: String) {
    Toast.makeText(MainActivity.getContext(), message, Toast.LENGTH_SHORT).show()
}