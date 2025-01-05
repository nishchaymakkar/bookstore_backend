package com.nishchay.bookstore.domain.dto

data class UserSignUpDto(
    val userId: Long?,
    val userEmail: String,
    val userPassword: String,
    val userName: String
)
