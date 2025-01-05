package com.nishchay.bookstore.domain.entity

import jakarta.persistence.*

@Entity
data class UserEntity (

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId : Long?,
    @Column(name = "user_email")
    val userEmail: String,
    @Column(name = "user_password")
    val userPassword: String,
    @Column(name = "user_name")
    val userName: String,
)