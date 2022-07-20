package com.example.books.models

import com.google.gson.annotations.SerializedName

data class Book (
    @SerializedName("id") val id: String?,
    @SerializedName("etag") val etag: String?,
    @SerializedName("volumeInfo") val volumeInfo: VolumeInfo
)