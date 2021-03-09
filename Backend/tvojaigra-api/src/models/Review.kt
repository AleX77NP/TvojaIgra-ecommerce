package com.tvojaigra.models

import java.util.*

data class Review(
    val user: String,
    val product: Product,
    val rating: Number,
    val text: String,
    val date: Date,
)
