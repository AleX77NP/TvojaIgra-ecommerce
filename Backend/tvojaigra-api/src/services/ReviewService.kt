package com.tvojaigra.services

import com.tvojaigra.models.Review
import com.tvojaigra.repositories.ReviewRepository

object ReviewService {

    fun addReview(review: Review) {
        ReviewRepository.addReview(review)
    }

    fun getProductReviews(productId: String): MutableList<Review>? {
        return ReviewRepository.getProductReviews(productId)
    }

    fun getReviewByProductAndUser(productId: String, user:String): Review? {
        return ReviewRepository.getReviewByProductAndUser(productId,user)
    }

    fun deleteReview(id: String) {
        ReviewRepository.deleteReview(id)
    }
}