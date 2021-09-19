package com.example.images.data.model

//Entity
data class Image(
    val id: Int,
    val name: String,
    val category: Category? = null
)