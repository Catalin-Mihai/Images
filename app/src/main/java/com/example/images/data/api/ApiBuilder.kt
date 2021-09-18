package com.example.images.data.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


object ApiBuilder {

    private var apiBuilder: ApiService? = null

    //Simulate a real API
    private const val BASE_URL = "https://pastebin.com/"

    val instance: ApiService
        get() {
            if (apiBuilder == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
                val moshi = Moshi.Builder()
                    .add(KotlinJsonAdapterFactory())
                    .build()
                val retrofit: Retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
                    .build()
                apiBuilder = retrofit.create(ApiService::class.java)
            }
            return apiBuilder!!
        }
}