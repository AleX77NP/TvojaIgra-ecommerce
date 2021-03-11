package com.tvojaigra.controllers

import com.tvojaigra.models.Product
import com.tvojaigra.models.api.Message
import com.tvojaigra.models.api.ProductsRes
import com.tvojaigra.services.ProductService
import com.tvojaigra.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.products() {
    route("/products") {
        get("/{id}") {
            val id = call.parameters["id"]
            if (id != null) {
                val product = ProductService.getProductById(id)
                if (product != null) {
                    call.respond(HttpStatusCode.OK, product)
                } else {
                    call.respond(HttpStatusCode.NotFound, Message("Product not found"))
                }
            } else {
                call.respond(HttpStatusCode.BadRequest, Message("Id not found."))
            }
        }

        get("/category/{category}") {
            val category = call.parameters["category"]
            if (category != null) {
                val products = ProductService.getProductsByCategory(category)
                call.respond(HttpStatusCode.OK, ProductsRes(products))
            }  else {
                call.respond(HttpStatusCode.BadRequest, Message("Category not found"))
            }
        }

        authenticate {
            post {
                val user = call.user
                user?.let {
                    if (!user.isAdmin) {
                        call.respond(HttpStatusCode.Unauthorized, Message("Only admin can add products"))
                    } else {
                        val product = call.receive<Product>()
                        ProductService.addProduct(product)
                        call.respond(HttpStatusCode.Created, Message("Product added"))
                    }
                }
            }

            put("/{id}") {
                val user = call.user
                user?.let {
                    if (!user.isAdmin) {
                        call.respond(HttpStatusCode.Unauthorized, Message("Only admin can edit products"))
                    } else {
                        val id = call.parameters["id"]
                        id?.let {
                            val product = call.receive<Product>()
                            ProductService.editProduct(id, product)
                            call.respond(HttpStatusCode.OK, Message("Product edited"))
                        }
                    }
                }
            }

            delete("/{id}") {
                val user = call.user
                user?.let {
                    if (!user.isAdmin) {
                        call.respond(HttpStatusCode.Unauthorized, Message("Only admin can delete products"))
                    } else {
                        val id = call.parameters["id"]
                        id?.let {
                            ProductService.deleteProduct(id)
                            call.respond(HttpStatusCode.OK, Message("Product deleted"))
                        }
                    }
                }
            }
        }
    }
}