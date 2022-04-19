package com.example.map.resource.map_resource_locator

import com.example.map.resource.map_resource_locator.data_model.Resource
import com.example.map.resource.map_resource_locator.get_data.Cache
import com.example.map.resource.map_resource_locator.get_data.CacheData
import com.example.map.resource.map_resource_locator.get_data.HttpUrls
import com.example.map.resource.map_resource_locator.get_data.RestCall
import com.example.map.resource.map_resource_locator.utils.deserialize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GetDataTest {
    @DisplayName("Retrieves the data")
    @Test
    fun `retrieves the data`() {
        withTestScope {
            val result = RestCall.callFor(HttpUrls.MAIN_DATA)
            result.status shouldBe RestCall.RestStatus.SUCCESS
        }
    }

    @Nested
    @DisplayName("With mock JSON data")
    inner class MockDataTest {
        @get:Rule
        val allData: List<Resource> = mockData.deserialize()

        @DisplayName("is the 473 data retrieved")
        @Test
        fun `is the 473 companyZoneId data retrieved`() {
            val targetData = allData.find { it.companyZoneId == 473 }
            targetData shouldBe expectedObjectFor473CompanyZoneId
        }

        @DisplayName("is the 412 data retrieved")
        @Test
        fun `is the 412 companyZoneId data retrieved`() {
            val targetData = allData.find { it.companyZoneId == 412 }
            targetData shouldBe expectedObjectFor412CompanyZoneId
        }
    }

    @DisplayName("When creating the cache")
    @Nested
    inner class CacheTest{
        @DisplayName("it is not empty")
        @Test
        fun `the cache is not empty after call`() {
            withTestScope {
                val cache = Cache.get()
                cache shouldNotBe CacheData()
            }
        }

        @DisplayName("it has the correct number of resources")
        @Test
        fun `the cache has all the resources`() {
            withTestScope {
                val cache = Cache.get()
                val resourcesSize = cache.resources.size
                resourcesSize shouldBe 77
            }
        }
    }
}