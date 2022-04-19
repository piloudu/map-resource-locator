package com.example.map.resource.map_resource_locator

import com.example.map.resource.map_resource_locator.data_model.Resource
import com.example.map.resource.map_resource_locator.get_data.HttpUrls
import com.example.map.resource.map_resource_locator.get_data.RestCall
import com.example.map.resource.map_resource_locator.utils.deserialize
import io.kotest.matchers.shouldBe
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
        @DisplayName("is the 473 data retrieved")
        @Test
        fun `is the 473 companyZoneId data retrieved`() {
            val data: List<Resource> = mock473CompanyZoneId.deserialize()
            data[0] shouldBe expectedObjectFor473CompanyZoneId
        }
    }
}