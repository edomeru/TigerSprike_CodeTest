package com.app.tiger.tigerspike_code_test.DataModel


import com.google.gson.annotations.SerializedName

data class JsonPicturesModel(
    val title: String,
    val link: String,
    val description: String,
    val modified: String,
    val generator: String,
    val items: List<Item>
)