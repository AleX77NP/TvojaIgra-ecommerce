package com.tvojaigra.models

import org.bson.codecs.pojo.annotations.BsonId
import java.security.Principal
import java.util.*

enum class Role {ADMIN, USER}

data class User(
    @BsonId val id: UUID = UUID.randomUUID(),
    val email: String,
    val password: String,
    val fullName: String,
    val address: String,
    val role: Role,
): Principal {
    override fun getName(): String {
        return fullName
    }
}
