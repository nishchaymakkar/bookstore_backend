package com.nishchay.bookstore.service

import com.nishchay.bookstore.domain.BookSummary
import com.nishchay.bookstore.domain.entity.BookEntity

interface BookService {

    fun createUpdate(isbn: String, bookSummary: BookSummary) : Pair<BookEntity,Boolean>

    fun list(authorId: Long?): List<BookEntity>
    fun get(isbn: String): BookEntity?
}