package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId

data class Review(
    @BsonId
    val _id: String? = null,
    val user: String,
    val product: String,
    val rating: Int,
    val text: String,
    val date: String,
)
