package com.tvojaigra.controllers

import com.tvojaigra.models.api.Message
import com.tvojaigra.models.api.OrdersRes
import com.tvojaigra.services.OrderService
import com.tvojaigra.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.orders() {
    route("/orders") {
        authenticate {
            get {
                val user = call.user
                user?.let {
                    if(user.isAdmin) {
                        val allOrders = OrderService.getOrders()
                        allOrders?.let {
                            call.respond(HttpStatusCode.OK, OrdersRes(allOrders))
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, Message("Only admin can view all orders."))
                    }
                }
            }
        }
    }
}