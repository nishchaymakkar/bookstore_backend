package com.nishchay.bookstore.controllers

import com.nishchay.bookstore.domain.dto.BookSummaryDto
import com.nishchay.bookstore.exceptions.InvalidAuthorException
import com.nishchay.bookstore.service.BookService
import com.nishchay.bookstore.toBookSummary
import com.nishchay.bookstore.toBookSummaryDto
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/books"])
class BooksController(val bookService: BookService) {

    @PutMapping(path = ["/{isbn}"])
    fun createFullUpdateBook(
        @PathVariable("isbn")isbn: String,
        @RequestBody book: BookSummaryDto
        ) :ResponseEntity<BookSummaryDto>{
       return try {
            val (savedBook,isCreated) = bookService.createUpdate(isbn,book.toBookSummary())
            val responseCode = if(isCreated) HttpStatus.CREATED else HttpStatus.OK
            ResponseEntity(savedBook.toBookSummaryDto(),responseCode)
        }catch (ex: InvalidAuthorException){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
        catch (ex: IllegalStateException){
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping
    fun readManyBooks(
        @RequestParam("author") authorId: Long?
    ): List<BookSummaryDto>{
        return bookService.list(authorId = authorId).map { it.toBookSummaryDto() }
    }

    @GetMapping(path = ["/{isbn}"])
    fun readOneBook(
        @PathVariable("isbn")isbn: String,
    ):ResponseEntity<BookSummaryDto>{
       return bookService.get(isbn)?.let {
            ResponseEntity( it.toBookSummaryDto(),HttpStatus.OK) }
            ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

}