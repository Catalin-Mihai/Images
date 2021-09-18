package com.example.images.data.api

import com.example.images.data.model.RequestResponse
import retrofit2.http.GET
import retrofit2.http.POST

//https://pastebin.com/raw/dQTwD0gJ

interface ApiService {

    @GET("raw/dQTwD0gJ")
    suspend fun getImagesData(): RequestResponse?
}