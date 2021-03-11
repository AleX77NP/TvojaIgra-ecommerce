package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Review(
    @BsonId
    val _id: String? = null,
    val user: String,
    val product: String,
    val rating: Number,
    val text: String,
    val date: Date,
)
