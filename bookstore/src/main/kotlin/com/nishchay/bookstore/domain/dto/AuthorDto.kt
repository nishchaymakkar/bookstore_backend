package com.nishchay.bookstore.domain.dto

import jakarta.persistence.Column

data class AuthorDto(
    val id: Long?,
    val name: String,
    val age: Int,
    val description: String,
    val image: String
)
