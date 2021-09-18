package com.example.images.data.model

import com.squareup.moshi.Json

data class RequestResponse(
    @Json(name = "all_images")
    val allImages: List<Image>,

    @Json(name = "categories")
    val categories: List<Category>,

    @Json(name = "my_images")
    val myImages: List<Int>,

    @Json(name = "best_images")
    val bestImages: List<Int>
)