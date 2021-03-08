package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Review(
    @BsonId val id: UUID = UUID.randomUUID(),
    val user: String,
    val product: Product,
    val rating: Number,
    val text: String,
    val date: Date,
)
