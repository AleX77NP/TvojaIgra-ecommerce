package com.tvojaigra.services

import com.tvojaigra.models.Order
import com.tvojaigra.repositories.OrderRepository

object OrderService {

    fun addOrder(order: Order) {
        OrderRepository.addOrder(order)
    }

    fun cancelOrder(id: String) {
        OrderRepository.cancelOrder(id)
    }

    fun getOrders(): MutableList<Order>? {
        return OrderRepository.getOrders()
    }

    fun getOrderById(id: String): Order? {
        return OrderRepository.getOrderById(id)
    }

    fun getUserOrders(user: String): MutableList<Order>? {
        return OrderRepository.getUserOrders(user)
    }

    fun updateOrderPaid(id: String) {
        OrderRepository.updateOrderPaid(id)
    }

    fun updateOrderDelivered(id: String) {
        OrderRepository.updateOrderDelivered(id)
    }
}