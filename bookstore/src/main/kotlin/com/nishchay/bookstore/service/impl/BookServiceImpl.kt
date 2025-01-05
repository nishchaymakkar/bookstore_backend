package com.nishchay.bookstore.service.impl

import com.nishchay.bookstore.domain.BookSummary
import com.nishchay.bookstore.domain.entity.BookEntity
import com.nishchay.bookstore.repositories.AuthorRepository
import com.nishchay.bookstore.repositories.BookRepository
import com.nishchay.bookstore.service.BookService
import com.nishchay.bookstore.toBookEntity
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class BookServiceImpl(
    val bookRepository: BookRepository,
    val authorRepository: AuthorRepository
) : BookService{

    @Transactional
    override fun createUpdate(isbn: String, bookSummary: BookSummary): Pair<BookEntity, Boolean> {
        val normalizedBook = bookSummary.copy(isbn = isbn)
        val isExists = bookRepository.existsById(isbn)
        val author = authorRepository.findByIdOrNull(normalizedBook.authorSummary.id)
        checkNotNull(author)
        val savedBook = bookRepository.save(normalizedBook.toBookEntity(author))

        return Pair(savedBook,!isExists)
    }

    override fun list(authorId: Long?): List<BookEntity> {
       return authorId?.let {
            bookRepository.findByAuthorEntityId(it)
        } ?: bookRepository.findAll()
    }

    override fun get(isbn: String): BookEntity? {
        return bookRepository.findByIdOrNull(isbn)
    }
}