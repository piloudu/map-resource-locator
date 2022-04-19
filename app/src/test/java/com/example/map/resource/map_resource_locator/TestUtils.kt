package com.example.map.resource.map_resource_locator

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain

private val dispatcher = TestCoroutineDispatcher()

internal fun withTestScope(scope: suspend CoroutineScope.() -> Unit) {
    runBlocking {
        Dispatchers.setMain(dispatcher)
        scope()
    }
}

val mock473CompanyZoneId = """
    {
    "id":"PT-LIS-A00445","name":"03ZB35","x":-9.145625,"y":38.71125,"licencePlate":"03ZB35","range":27,
    "batteryLevel":36,"helmets":2,"model":"Askoll","resourceImageId":"vehicle_gen_ecooltra",
    "resourcesImagesUrls":["vehicle_gen_ecooltra"],"realTimeData":true,"resourceType":"MOPED","companyZoneId":473
    }
""".trimIndent()

val mock412CompanyZoneId = """
    {
    "id":"222","name":"222 - PraÃ§a da Figueira","x":-9.13828,"y":38.71383,"realTimeData":true,
    "station":true,"availableResources":6,"spacesAvailable":24,"allowDropoff":true,"companyZoneId":412,
    "bikesAvailable":6
    }
""".trimIndent()