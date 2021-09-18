package com.example.images.data.api

import com.example.images.data.model.RequestResponse
import retrofit2.http.GET
import retrofit2.http.POST

//https://pastebin.com/kswq5MMs

interface ApiService {

    @GET("raw/kswq5MMs")
    suspend fun getImagesData(): RequestResponse?
}