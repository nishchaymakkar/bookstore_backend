package com.nishchay.bookstore.controllers

import com.nishchay.bookstore.domain.AuthorUpdateRequest
import com.nishchay.bookstore.domain.dto.AuthorDto
import com.nishchay.bookstore.domain.dto.AuthorUpdateRequestDto
import com.nishchay.bookstore.service.AuthorService
import com.nishchay.bookstore.toAuthorDto
import com.nishchay.bookstore.toAuthorEntity
import com.nishchay.bookstore.toAuthorUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(path = ["/v1/authors"] )
class AuthorsController(private val authorService: AuthorService) {

    @PostMapping
    fun createAuthor(@RequestBody authorDto: AuthorDto): ResponseEntity<AuthorDto>{
        try {
            val createdAuthor = authorService.create(authorDto.toAuthorEntity()).toAuthorDto()
            return ResponseEntity(createdAuthor, HttpStatus.CREATED)

        } catch (ex: IllegalStateException){
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping
    fun readManyAuthor() : List<AuthorDto> {
        return authorService.list().map { it.toAuthorDto() }
    }

    @GetMapping("/page")
    fun listByPage(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10")size: Int): Page<AuthorDto> {
        return authorService.listByPage(page,size).map { it.toAuthorDto() }
    }

    @GetMapping(path = ["/{id}"])
    fun readOneAuthor(@PathVariable("id") id: Long): ResponseEntity<AuthorDto> {
       val foundAuthor = authorService.get(id)?.toAuthorDto()
       return foundAuthor?.let {
            ResponseEntity(it,HttpStatus.OK)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping(path = ["/{id}"])
    fun fullUpdateAuthor(
        @PathVariable("id") id: Long,
        @RequestBody authorDto: AuthorDto
    ): ResponseEntity<AuthorDto> {
        return try {
            val updatedAuthor = authorService.fullUpdate(id,authorDto.toAuthorEntity())
           ResponseEntity(updatedAuthor.toAuthorDto(),HttpStatus.OK)
        } catch (ex: IllegalStateException){
             ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping(path = ["/{id}"])
    fun partialUpdateAuthor(
        @PathVariable("id") id: Long,
        @RequestBody authorUpdateRequest: AuthorUpdateRequestDto
    ): ResponseEntity<AuthorDto> {
        return try {
          val updaterAuthor =  authorService.partialUpdate(id,authorUpdateRequest.toAuthorUpdateRequest())
            ResponseEntity(updaterAuthor.toAuthorDto(),HttpStatus.OK)
        } catch (ex: IllegalStateException){
            ResponseEntity(HttpStatus.BAD_REQUEST)
        }
    }

    @DeleteMapping(path = ["/{id}"])
    fun deleteAuthor(
        @PathVariable("id") id: Long
    ) : ResponseEntity<Unit> {
        authorService.delete(id)

        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}