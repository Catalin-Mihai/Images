package com.example.images.data.model

import com.squareup.moshi.Json

data class Category(
    val id: Int,
    val name: String,

    @Json(name = "images")
    val imagesIds: List<Int>
)
