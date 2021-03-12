package com.tvojaigra.repositories

import com.tvojaigra.db.MongoDb
import com.tvojaigra.models.Review
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection


object ReviewRepository {

    private val reviewCol = MongoDb.getDatabase()?.getCollection<Review>()

    fun addReview(review: Review) {
        reviewCol?.insertOne(review)
    }

    fun getProductReviews(productId: String): MutableList<Review>? {
        return reviewCol?.find(Review::product eq productId)?.toMutableList()
    }

    fun getReviewByProductAndUser(productId: String, user:String): Review? {
        return reviewCol?.findOne(Review::product eq productId, Review::user eq user)
    }

    fun deleteReview(id: String) {
        reviewCol?.deleteOne(Review::_id eq id)
    }

}