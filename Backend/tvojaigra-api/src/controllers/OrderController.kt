package com.tvojaigra.controllers

import com.tvojaigra.models.Order
import com.tvojaigra.models.api.Message
import com.tvojaigra.models.api.OrdersRes
import com.tvojaigra.services.OrderService
import com.tvojaigra.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
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
            get("/user/{user}") {
                val user = call.parameters["user"]
                user?.let {
                    val userOrders = OrderService.getUserOrders(user)
                    userOrders?.let {
                        call.respond(HttpStatusCode.OK, OrdersRes(userOrders))
                    }
                }
            }

            get("/{id}") {
                val id = call.parameters["id"]
                id?.let {
                    val order = OrderService.getOrderById(id)
                    if (order != null) {
                        call.respond(HttpStatusCode.OK, order)
                    } else {
                        call.respond(HttpStatusCode.NotFound, Message("No order with provided id."))
                    }
                }
            }

            post {
                val newOrder = call.receive<Order>()
                val res = OrderService.addOrder(newOrder)
                print(res)
                call.respond(HttpStatusCode.Created, newOrder)
            }

            put("/cancel/{id}") {
                val id = call.parameters["id"]
                id?.let {
                    OrderService.cancelOrder(id)
                    call.respond(HttpStatusCode.OK, Message("Your order has been canceled."))
                }
            }

            put("/pay/{id}") {
                val id = call.parameters["id"]
                id?.let {
                    OrderService.updateOrderPaid(id)
                    call.respond(HttpStatusCode.OK, Message("Order status updated to paid."))
                }
            }

            put("/deliver/{id}") {
                val user = call.user
                user?.let {
                    if (user.isAdmin) {
                        val id = call.parameters["id"]
                        id?.let {
                            OrderService.updateOrderDelivered(id)
                            call.respond(HttpStatusCode.OK, Message("Order status updated to delivered."))
                        }
                    } else {
                        call.respond(HttpStatusCode.Unauthorized, Message("Only admins can update orders to delivered."))
                    }
                }
            }
        }
    }
}