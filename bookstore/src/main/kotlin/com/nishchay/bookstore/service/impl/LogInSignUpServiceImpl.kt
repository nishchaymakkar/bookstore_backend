package com.nishchay.bookstore.service.impl

import com.nishchay.bookstore.domain.dto.LoginResponse
import com.nishchay.bookstore.domain.dto.UserLoginDto
import com.nishchay.bookstore.domain.dto.UserSignUpDto
import com.nishchay.bookstore.domain.entity.UserEntity
import com.nishchay.bookstore.repositories.UserRepository
import com.nishchay.bookstore.service.LogInSignUpService
import com.nishchay.bookstore.toUserEntity
import com.nishchay.bookstore.util.JwtTokenUtil
import org.apache.catalina.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import kotlin.math.sign

class LogInSignUpServiceImpl(
    private val userRepository: UserRepository,
    private val byBCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtTokenUtil: JwtTokenUtil
) : LogInSignUpService {
    override fun registerUser(userSignUpDto: UserSignUpDto) {
        val user = UserEntity(
            userPassword = byBCryptPasswordEncoder.encode(userSignUpDto.userPassword),
            userId = userSignUpDto.userId,
            userName = userSignUpDto.userName,
            userEmail = userSignUpDto.userEmail
        )
        userRepository.save(user)


    }
    override fun login(loginDto: UserLoginDto): LoginResponse {
        TODO("Not yet implemented")
    }
}