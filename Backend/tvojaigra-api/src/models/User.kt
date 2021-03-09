package com.tvojaigra.models

import java.security.Principal


data class User(
    val email: String,
    val password: String,
    val fullName: String,
    val address: String,
    val role: String = "user",
): Principal {
    override fun getName(): String {
        return fullName
    }
}
