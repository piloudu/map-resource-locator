package com.example.map.resource.map_resource_locator.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.map.resource.map_resource_locator.data_model.Resource
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

@Preview(showBackground = true)
@Composable
fun PopupInfoPreview() {
    val resource = mockResource
    PopupInfo(
        resource = mockResource
    )
}


@Composable
fun PopupInfo(
    modifier: Modifier = Modifier,
    resource: Resource
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White)
            .padding(16.dp)
    ) {
        val resourceProperties = resource::class.memberProperties
        val nonNullProperties = resourceProperties.filterNot { it.getter.call(it) == null }
        nonNullProperties.forEach {
            val suffix = if (it.getter.returnType is Number) " uds" else ""
            PopupInfoText(
                key = it.name,
                value = it.getter.call(it).toString() + suffix
            )
        }
    }
}

@Composable
fun PopupInfoText(
    modifier: Modifier = Modifier,
    key: String,
    value: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Start
    ) {
        Text(
            text = key,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(Modifier.width(8.dp))
        Text(
            text = value,
            color = Color.Gray
        )
    }
}