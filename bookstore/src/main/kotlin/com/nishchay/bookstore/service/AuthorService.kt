package com.nishchay.bookstore.service

import com.nishchay.bookstore.domain.AuthorUpdateRequest
import com.nishchay.bookstore.domain.entity.AuthorEntity
import org.springframework.data.domain.Page


interface AuthorService {
    fun create(authorEntity: AuthorEntity): AuthorEntity

    fun list() : List<AuthorEntity>

    fun get(id: Long): AuthorEntity?

    fun listByPage(page: Int, size: Int): Page<AuthorEntity>

    fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity

    fun partialUpdate(id: Long, authorUpdate: AuthorUpdateRequest): AuthorEntity

    fun delete(id: Long)
}