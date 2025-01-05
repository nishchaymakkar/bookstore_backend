package com.nishchay.bookstore.repositories

import com.nishchay.bookstore.domain.entity.UserEntity
import org.apache.catalina.User
import org.apache.el.stream.Optional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<UserEntity,Long> {

    fun findByUserEmail(email: String): UserEntity?

}