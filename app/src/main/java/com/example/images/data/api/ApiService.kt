package com.example.images.data.api

import com.example.images.data.model.RequestResponse
import retrofit2.http.*

// Link: https://pastebin.com/kswq5MMs

interface ApiService {

    //It won't work with the POST Verb as requested in the test document. It returns 400 error for bad request.
    //So I decided to make a GET request to actually get some data in order to test tha App.
    //I modified the JSON to be correctly parsed by Moshi. Anyway, it was very wrong displayed on the PDF file...
    //... so I assumed that the correct version would be the one from this link.


    //Still trying this one for test purposes.
    @FormUrlEncoded
    @POST("raw/kswq5MMs")
    suspend fun testPostRequest(@Field("lang") lang: String, @Field("version") version: String): RequestResponse?


    @GET("raw/kswq5MMs")
    suspend fun getImagesData(): RequestResponse?
}