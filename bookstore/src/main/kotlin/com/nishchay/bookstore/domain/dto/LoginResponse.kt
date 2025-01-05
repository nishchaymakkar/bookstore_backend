package com.nishchay.bookstore.domain.dto

data class LoginResponse(
    private val sessionToken: String,
    private val userId: Long
)
