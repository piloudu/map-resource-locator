package com.example.map.resource.map_resource_locator

import okhttp3.OkHttpClient
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GetDataTest {

    @Nested
    @DisplayName("With mock JSON data")
    inner class MockDataTest {
        @DisplayName("is the 473 data retrieved")
        @Test
        fun `is the 473 companyZoneId data retrieved`() {
            val client = OkHttpClient()
            val data = client.newBuilder().
        }
    }
}