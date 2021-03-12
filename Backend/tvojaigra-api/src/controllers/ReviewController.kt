package com.tvojaigra.controllers

import com.tvojaigra.models.Review
import com.tvojaigra.models.api.Message
import com.tvojaigra.models.api.ReviewsRes
import com.tvojaigra.services.ReviewService
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.reviews() {
    route("/reviews") {
        authenticate {
            post {
                val review = call.receive<Review>()
                val existingReview = ReviewService.getReviewByProductAndUser(review.product, review.user)
                if (existingReview != null) {
                    call.respond(HttpStatusCode.BadRequest, Message("You already reviewed this product."))
                } else {
                    ReviewService.addReview(review)
                    call.respond(HttpStatusCode.Created, Message("Review added."))
                }
            }

            delete("/{id}") {
                val id = call.parameters["id"]
                id?.let {
                    ReviewService.deleteReview(id)
                    call.respond(HttpStatusCode.OK, Message("Review deleted."))
                }
            }
        }
        get("/product/{id}") {
            val productId = call.parameters["id"]
            productId?.let {
                val reviews = ReviewService.getProductReviews(productId)
                call.respond(HttpStatusCode.OK, ReviewsRes(reviews))
            }
        }
    }
}