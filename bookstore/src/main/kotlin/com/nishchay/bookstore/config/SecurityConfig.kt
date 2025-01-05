package com.nishchay.bookstore.config

import com.nishchay.bookstore.service.CustomUserDetailsService
import com.nishchay.bookstore.util.JwtTokenUtil
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val userDetailsService: CustomUserDetailsService,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter

) {

    @Bean
    fun customUserDetailsService(): CustomUserDetailsService {
        return userDetailsService
    }

    @Bean
    fun byCryptPasswordEncoder(): BCryptPasswordEncoder {
        return bCryptPasswordEncoder
    }
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/bookstore/auth/**").permitAll()
                    .requestMatchers(
                        "/bookstore/userRegister",
                        "/bookstore/login"
                    ).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authManager(http: HttpSecurity): AuthenticationManager {
        val authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder::class.java)
        authenticationManagerBuilder
            .userDetailsService(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder)

        return authenticationManagerBuilder.build()
    }
}