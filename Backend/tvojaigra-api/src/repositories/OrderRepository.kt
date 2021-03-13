package com.tvojaigra.repositories

import com.mongodb.client.result.InsertOneResult
import com.tvojaigra.db.MongoDb
import com.tvojaigra.models.Order
import org.litote.kmongo.*
import java.util.*

object OrderRepository {

    private val orderCol = MongoDb.getDatabase()?.getCollection<Order>()

    fun addOrder(order: Order): InsertOneResult? {
        return orderCol?.insertOne(order)
    }

    fun cancelOrder(id: String) {
        orderCol?.updateOne(Order::_id eq id, setValue(Order::status, "canceled"))
    }

    fun getOrders(): MutableList<Order>? {
        return orderCol?.find()?.toMutableList()
    }

    fun getOrderById(id: String): Order? {
        return orderCol?.findOneById(id)
    }

    fun getUserOrders(user: String): MutableList<Order>? {
        return orderCol?.find(Order::user eq user)?.toMutableList()
    }

    fun updateOrderPaid(id: String) {
        val dateNow = Calendar.getInstance().time.toString()
        orderCol?.updateOne(Order::_id eq id, set(Order::isPaid setTo true, Order::paidDate setTo dateNow, Order::status setTo "paid"))
    }

    fun updateOrderDelivered(id: String) {
        val dateNow = Calendar.getInstance().time.toString()
        orderCol?.updateOne(Order::_id eq id, set(Order::isDelivered setTo true, Order::deliverDate setTo dateNow, Order::status setTo "delivered"))
    }
}