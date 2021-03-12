package com.tvojaigra.repositories

import com.tvojaigra.db.MongoDb
import com.tvojaigra.models.User
import org.litote.kmongo.eq
import org.litote.kmongo.findOne
import org.litote.kmongo.getCollection

object UserRepository {

    private val userCol = MongoDb.getDatabase()?.getCollection<User>()

    fun addUser(user: User) {
        userCol?.insertOne(user)
    }

    fun getUserByEmail(email: String): User? {
        return userCol?.findOne(User::email eq email)
    }
}