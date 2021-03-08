package com.tvojaigra.controllers

import com.tvojaigra.auth.JwtConfig
import com.tvojaigra.models.Role
import com.tvojaigra.models.User
import com.tvojaigra.models.api.LoginInfo
import com.tvojaigra.models.api.TokenRes
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.util.*

fun Routing.auth() {
    post("/auth/login") {
        val info = call.receive<LoginInfo>()
        val user = User(
            UUID.randomUUID(),
            info.email,
            info.password,
            "John Doe",
            "adresa john doe",
            role = Role.USER
        );
        val token = JwtConfig.generateToken(user)
        call.respond(HttpStatusCode.OK, TokenRes(token))
    }
}