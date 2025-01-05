package com.nishchay.bookstore.repositories

import com.nishchay.bookstore.domain.entity.AuthorEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorRepository : JpaRepository<AuthorEntity,Long?>