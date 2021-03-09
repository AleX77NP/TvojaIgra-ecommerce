package com.tvojaigra

import com.tvojaigra.models.api.UserRes
import io.ktor.application.*
import io.ktor.auth.*


val ApplicationCall.user get() = authentication.principal<UserRes>()