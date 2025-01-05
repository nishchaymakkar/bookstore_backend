package com.nishchay.bookstore.domain.dto

data class LoginResponse(
    val sessionToken: String,
     val userId: Long
)
