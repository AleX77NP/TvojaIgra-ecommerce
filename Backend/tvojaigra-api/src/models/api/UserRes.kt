package com.tvojaigra.models.api

import io.ktor.auth.*

data class UserRes(
    val email: String,
    val fullName: String,
    val address: String
) : Principal
