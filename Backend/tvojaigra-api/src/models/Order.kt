package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.util.*

data class Order(
    @BsonId
    val _id: String? = null,
    val user: String,
    val products: List<Product>,
    val totalPrice: Float,
    val discount: Int = 0,
    val taxPrice: Float,
    val shippingPrice: Float,
    val paymentMethod: String,
    val status: String,
    val billingAddress: String,
    val shippingAddress: String,
    val isPaid: Boolean,
    val paidDate: Date?,
    val isDelivered: Boolean,
    val deliverDate: Date?,
)
