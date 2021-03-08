package com.tvojaigra

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.features.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.auth.*
import com.fasterxml.jackson.databind.*
import com.tvojaigra.auth.JwtConfig
import com.tvojaigra.controllers.auth
import com.tvojaigra.models.api.UserRes
import io.ktor.auth.jwt.*
import io.ktor.jackson.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CORS) {
        method(HttpMethod.Options)
        method(HttpMethod.Put)
        method(HttpMethod.Delete)
        method(HttpMethod.Patch)
        header(HttpHeaders.Authorization)
        header("MyCustomHeader")
        allowCredentials = true
        anyHost() // @TODO: Don't do this in production if possible. Try to limit it.
    }

    install(Authentication) {
        jwt {
            verifier(JwtConfig.verifier)
            realm = "com.tvojaigra"
            validate {
                val email = it.payload.getClaim("email").asString()
                val fullName = it.payload.getClaim("fullName").asString()
                val address = it.payload.getClaim("address").asString()
                if (email != null && fullName != null && address != null) {
                    UserRes(email, fullName, address)
                } else {
                    null
                }
            }
        }
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/json/jackson") {
            call.respond(mapOf("hello" to "world"))
        }

        this.auth()
    }
}

