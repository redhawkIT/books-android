package com.example.books.viewModels

import com.example.books.controllers.APIController
import com.example.books.models.Book
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainViewModel {

    fun loadBooksByIndex(index: Int, callback: (String?, List<Book>?) -> Unit)
    {
        APIController.defaults.loadBook(index) { err, obj ->
            if (err != null)
            {
                callback(err, null)
            }
            else
            {
                val gson = Gson()
                val listType: Type = object : TypeToken<List<Book?>?>() {}.type
                callback(null, gson.fromJson(obj!!.get("items").asJsonArray, listType))
            }
        }
    }
}