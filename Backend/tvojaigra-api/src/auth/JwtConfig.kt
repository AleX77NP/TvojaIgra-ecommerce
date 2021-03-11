package com.tvojaigra.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.tvojaigra.models.User
import java.util.*

object JwtConfig {
    private const val secret = "mysupersecret" //do not do this in production
    private const val issuer = "com.tvojaigra"
    private const val validityInMs = 36_000_00 * 24
    private val algo = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algo)
        .withIssuer(issuer)
        .build()

    fun generateToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("email", user.email)
        .withClaim("fullName", user.fullName)
        .withClaim("address", user.address)
        .withClaim("isAdmin", user.isAdmin)
        .withExpiresAt(getExp())
        .sign(algo)

    private fun getExp() = Date(System.currentTimeMillis() + validityInMs)
}