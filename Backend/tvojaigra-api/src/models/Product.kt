package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Product(
    @BsonId val id: UUID = UUID.randomUUID(),
    val title: String,
    val price: Number,
    val available: Number,
    val description: Number,
    val image: String,
    val brand: String,
    val category: String,
    val reviews: List<Review>
)
