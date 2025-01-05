package com.nishchay.bookstore.domain


data class BookSummary(
    val isbn: String,
    val title: String,
    val description: String,
    val image: String,
    val authorSummary: AuthorSummary
)
