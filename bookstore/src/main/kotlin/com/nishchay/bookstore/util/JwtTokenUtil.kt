package com.nishchay.bookstore.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.Date

private const val key : String = "nahinpH6L13PejYffzqajC6Kco+G8bqneE+hTpriZtEY"
private const val EXPIRATION_TIME = 31556952000L

@Component
class JwtTokenUtil {

    fun generateToken(userId : Long) : String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setIssuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
            .signWith(SignatureAlgorithm.HS256, key)
            .compact()
    }

    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(key)
            .build()
            .parseClaimsJwt(token)
            .body
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            getClaimsFromToken(token)
            true
        } catch (e: ExpiredJwtException /*| MalformedJwtException | UnsupportedJwtException | IllegalArgumentException*/) {
            false
        }
    }

}