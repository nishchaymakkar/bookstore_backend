package com.nishchay.bookstore.service

import com.nishchay.bookstore.domain.dto.LoginResponse
import com.nishchay.bookstore.domain.dto.UserLoginDto
import com.nishchay.bookstore.domain.dto.UserSignUpDto

interface LogInSignUpService {

    fun registerUser(userSignUpDto: UserSignUpDto)

    fun login(loginDto: UserLoginDto): LoginResponse
}