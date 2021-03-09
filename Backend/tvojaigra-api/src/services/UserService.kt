package com.tvojaigra.services

import com.tvojaigra.models.User
import com.tvojaigra.repositories.UserRepository

object UserService {

    fun addUser(user: User) {
        UserRepository.addUser(user)
    }

    fun getUserByEmail(email: String): User? {
        return UserRepository.getUserByEmail(email)
    }
}