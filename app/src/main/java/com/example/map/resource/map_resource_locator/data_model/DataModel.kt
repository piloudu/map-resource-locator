package com.example.map.resource.map_resource_locator.data_model

data class Resource(
    val id: String?,
    val name: String?,
    val x: String?,
    val y: String?,
    val licencePlate: String?,
    val resourceType: String?,
    val companyZoneId: String?,
    val model: String?,
    val resourceImageId: String?,
    val resourcesImagesUrls: List<String>,
    val range: Int?,
    val batteryLevel: Int?,
    val helmets: Int?,
    val availableResources: Int?,
    val spacesAvailable: Int?,
    val bikesAvailable: Int?,
    val allowDropOff: Boolean?,
    val realTimeData: Boolean?,
    val station: Boolean?
)