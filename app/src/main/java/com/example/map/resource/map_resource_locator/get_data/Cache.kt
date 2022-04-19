package com.example.map.resource.map_resource_locator.get_data

import com.example.map.resource.map_resource_locator.data_model.Resource
import com.example.map.resource.map_resource_locator.utils.deserialize
import com.example.map.resource.map_resource_locator.view_model.MainViewModelInstance

object Cache {
    suspend fun get(): CacheData {
        val cache = MainViewModelInstance.state.value.cache
        return if (cache.resources.isEmpty())
            getNewCache()
        else cache
    }

    private suspend fun getNewCache(): CacheData {
        val restResult = RestCall.callFor(HttpUrls.MAIN_DATA)

        return if (restResult.isSuccess())
            CacheData(restResult.message.deserialize())
        else CacheData()
    }
}

data class CacheData(
    val resources: List<Resource> = emptyList()
)