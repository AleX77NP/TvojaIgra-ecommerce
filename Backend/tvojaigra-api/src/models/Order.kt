package com.tvojaigra.models

import java.util.*

data class Order(
    val user: String,
    val products: List<Product>,
    val totalPrice: Number,
    val discount: Number = 0,
    val taxPrice: Number,
    val shippingPrice: Number,
    val paymentMethod: String,
    val status: String,
    val billingAddress: String,
    val shippingAddress: String,
    val isPaid: Boolean,
    val paidDate: Date?,
    val isDelivered: Boolean,
    val deliverDate: Date?,
)
