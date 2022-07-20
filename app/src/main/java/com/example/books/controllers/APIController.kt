package com.example.books.controllers

import com.example.books.managers.APIInterface
import com.example.books.managers.ServiceBuilder
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class APIController {

    companion object {
        val defaults: APIController
            get() {
                return APIController()
            }
    }

    fun generateParams(params: Map<String, String>? = null): Map<String, String> = (params ?: HashMap<String, String>()).toMutableMap()

    fun loadBook(index: Int, callback: (String?, JsonObject?) -> Unit)
    {
        val service = ServiceBuilder.buildService("https://www.googleapis.com/books/v1/", APIInterface::class.java)

        val param = HashMap<String, String>()
        //q=flowers&startIndex=0&maxResults=10
        param.put("q", "flowers")
        param.put("startIndex", index.toString())
        param.put("maxResults", "10")

        service.getBooksByIndex(generateParams(param))
            .enqueue(object : Callback<JsonObject>
            {
                override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                    if (response.isSuccessful)
                    {
                        val resp = response.body()

                        if (resp!!.has("error"))
                        {
                            callback(resp.get("error").asJsonObject.get("message").asString, null)
                        }
                        else
                        {
                            callback(null, resp)
                        }
                    }
                    else
                    {
                        callback("Request is not successful", null)
                    }
                }

                override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                    callback(t.localizedMessage, null)
                }

            })

    }
}