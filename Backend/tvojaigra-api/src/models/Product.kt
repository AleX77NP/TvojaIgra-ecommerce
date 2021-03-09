package com.tvojaigra.models

data class Product(
    val title: String,
    val price: Number,
    val available: Number,
    val description: Number,
    val image: String,
    val brand: String,
    val category: String,
    val reviews: List<Review>
)
