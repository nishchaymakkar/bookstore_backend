package com.nishchay.bookstore

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@EnableWebSecurity
@SpringBootApplication
class BookstoreApplication

fun main(args: Array<String>) {
	runApplication<BookstoreApplication>(*args)
}