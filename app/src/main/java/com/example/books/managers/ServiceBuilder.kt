package com.example.books.managers

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {

    private val client = OkHttpClient.Builder().build()
    private lateinit var retrofit: Retrofit

    fun<T> buildService(baseURL: String, service: Class<T>): T{
        val gsonBuilder = GsonBuilder()

        retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
            .client(client)
            .build()
        return retrofit.create(service)
    }
}