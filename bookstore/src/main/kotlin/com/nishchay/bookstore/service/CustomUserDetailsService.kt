package com.nishchay.bookstore.service

import com.nishchay.bookstore.repositories.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

interface CustomUserDetailsService: UserDetailsService{

    fun loadUserByUserName(userEmail: String): UserDetails

}