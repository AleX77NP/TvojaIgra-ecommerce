package com.tvojaigra.controllers

import com.tvojaigra.auth.JwtConfig
import com.tvojaigra.models.User
import com.tvojaigra.models.api.LoginInfo
import com.tvojaigra.models.api.Message
import com.tvojaigra.models.api.TokenRes
import com.tvojaigra.models.api.UserRes
import com.tvojaigra.services.UserService
import com.tvojaigra.user
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.mindrot.jbcrypt.BCrypt

fun Routing.auth() {
    post("/auth/login") {
        val info = call.receive<LoginInfo>()
        val user: User? = UserService.getUserByEmail(info.email)
        if (user != null) {
            if(BCrypt.checkpw(info.password, user.password)) {
                val token = JwtConfig.generateToken(user)
                call.respond(HttpStatusCode.OK, TokenRes(token))
            } else {
                call.respond(HttpStatusCode.BadRequest, Message("Incorrect password."))
            }
        } else {
            call.respond(HttpStatusCode.NotFound, Message("User not found."))
        }
    }

    post("/auth/register") {
        val user = call.receive<User>()
        val existingUser = UserService.getUserByEmail(user.email)
        if (existingUser != null) {
            call.respond(HttpStatusCode.BadRequest, Message("Email is already in use."))
        } else {
            user.password = UserService.hashPassword(user.password).toString()
            UserService.addUser(user)
            call.respond(HttpStatusCode.OK, Message("Registration success, You can login now."))
        }
    }

    authenticate {
        get("/auth/me") {
            val user: UserRes? = call.user
            if (user != null) {
            call.respond(user)
            } else {
                call.respond(HttpStatusCode.InternalServerError, Message("Something went wrong."))
            }
        }
    }
}