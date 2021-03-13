package com.tvojaigra.services

import com.tvojaigra.models.User
import com.tvojaigra.repositories.UserRepository
import org.mindrot.jbcrypt.BCrypt

object UserService {

    fun addUser(user: User) {
        UserRepository.addUser(user)
    }

    fun getUserByEmail(email: String): User? {
        return UserRepository.getUserByEmail(email)
    }

    fun hashPassword(password: String): String? {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}