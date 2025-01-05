package com.nishchay.bookstore.repositories

import com.nishchay.bookstore.domain.entity.BookEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BookRepository: JpaRepository<BookEntity,String> {
    fun findByAuthorEntityId(id: Long): List<BookEntity>
}