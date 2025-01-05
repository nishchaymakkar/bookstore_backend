package com.nishchay.bookstore.service.impl

import CustomUserDetails
import com.nishchay.bookstore.repositories.UserRepository
import com.nishchay.bookstore.service.CustomUserDetailsService
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class CustomUserDetailsServiceImpl(
    private val userRepository: UserRepository
): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUserEmail(username)
            ?: throw UsernameNotFoundException("User not found")
        return user.userId?.let { CustomUserDetails(it) }!!
    }
}