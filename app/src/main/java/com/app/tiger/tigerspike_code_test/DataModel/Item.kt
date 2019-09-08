package com.app.tiger.tigerspike_code_test.DataModel


import com.google.gson.annotations.SerializedName

data class Item(
    val title: String,
    val link: String,
    val media: Media,
    @SerializedName("date_taken")
    val dateTaken: String,
    val description: String,
    val published: String,
    val author: String,
    @SerializedName("author_id")
    val authorId: String,
    val tags: String
)