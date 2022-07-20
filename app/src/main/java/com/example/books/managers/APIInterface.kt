package com.example.books.managers

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface APIInterface {
    @GET("volumes")
    fun getBooksByIndex(@QueryMap params: Map<String, String>): Call<JsonObject>
}