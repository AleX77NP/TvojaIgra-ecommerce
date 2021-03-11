package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.security.Principal


data class User(
    @BsonId
    val _id: String? = null,
    val email: String,
    val password: String,
    val fullName: String,
    val address: String,
    val isAdmin: Boolean = false,
): Principal {
    override fun getName(): String {
        return fullName
    }
}
