package com.example.map.resource.map_resource_locator.data_model

import com.fasterxml.jackson.annotation.JsonAlias

data class Resource(
    @JsonAlias("x")
    val xPosition: Double? = null,
    @JsonAlias("y")
    val yPosition: Double? = null,
    val companyZoneId: Int? = null,
    val range: Int? = null,
    val batteryLevel: Int? = null,
    val helmets: Int? = null,
    val availableResources: Int? = null,
    val spacesAvailable: Int? = null,
    val bikesAvailable: Int? = null,
    val id: String? = null,
    val name: String? = null,
    val licencePlate: String? = null,
    val resourceType: String? = null,
    val model: String? = null,
    val resourceImageId: String? = null,
    val resourcesImagesUrls: List<String>? = null,
    @JsonAlias("allowDropoff")
    val allowDropOff: Boolean? = null,
    val realTimeData: Boolean? = null,
    val station: Boolean? = null
)