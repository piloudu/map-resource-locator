package com.example.map.resource.map_resource_locator.data_model

data class Resource(
    val companyZoneId: Int,
    val x: Double,
    val y: Double,
    val id: String,
    val name: String,
    val licencePlate: String? = null,
    val resourceType: String? = null,
    val model: String? = null,
    val resourceImageId: String? = null,
    val resourcesImagesUrls: List<String>? = null,
    val range: Int? = null,
    val batteryLevel: Int? = null,
    val helmets: Int? = null,
    val availableResources: Int? = null,
    val spacesAvailable: Int? = null,
    val bikesAvailable: Int? = null,
    val allowDropOff: Boolean? = null,
    val realTimeData: Boolean? = null,
    val station: Boolean? = null
)