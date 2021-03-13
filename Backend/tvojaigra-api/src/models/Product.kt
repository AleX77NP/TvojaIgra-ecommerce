package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId

data class Product(
    @BsonId
    val _id: String? = null,
    val title: String,
    val price: Float,
    val available: Int,
    val description: String,
    val image: String,
    val brand: String,
    val category: String,
)

