package com.nishchay.bookstore.domain.dto

data class UserLoginDto(
    private val userEmail: String,
    private val userPassword: String
)
