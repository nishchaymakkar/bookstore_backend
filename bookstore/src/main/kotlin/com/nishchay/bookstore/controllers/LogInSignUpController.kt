package com.nishchay.bookstore.controllers

import com.nishchay.bookstore.domain.dto.LoginResponse
import com.nishchay.bookstore.domain.dto.UserLoginDto
import com.nishchay.bookstore.domain.dto.UserSignUpDto
import com.nishchay.bookstore.service.LogInSignUpService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/bookstore")
class LogInSignUpController(
    private val logInSignUpService: LogInSignUpService
) {

    @PostMapping("/userRegister")
    fun registerUser(@RequestBody userSignUpDto: UserSignUpDto) : ResponseEntity<HttpStatus>{
        return try {
            logInSignUpService.registerUser(userSignUpDto)
            ResponseEntity.status(HttpStatus.CREATED).build()
        }catch (e: Exception) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginDto: UserLoginDto): ResponseEntity<LoginResponse>{
        return try {
          val loginResponse =  logInSignUpService.login(userLoginDto)
            ResponseEntity(loginResponse, HttpStatus.CREATED)
        }catch (e: Exception){
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }
}