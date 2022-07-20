package com.example.books.models

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class VolumeInfo (
    @SerializedName("title") val title: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("imageLinks") val imageLinks: JsonObject?

    )