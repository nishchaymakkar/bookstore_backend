package com.nishchay.bookstore

import com.nishchay.bookstore.domain.AuthorSummary
import com.nishchay.bookstore.domain.AuthorUpdateRequest
import com.nishchay.bookstore.domain.BookSummary
import com.nishchay.bookstore.domain.dto.*
import com.nishchay.bookstore.domain.entity.AuthorEntity
import com.nishchay.bookstore.domain.entity.BookEntity
import com.nishchay.bookstore.domain.entity.UserEntity
import com.nishchay.bookstore.exceptions.InvalidAuthorException

fun AuthorEntity.toAuthorDto() = AuthorDto(
        id = this.id,
        name = this.name,
        description = this.description,
        age = this.age,
        image = this.image
    )



fun AuthorDto.toAuthorEntity() = AuthorEntity(
    id = this.id,
    name = this.name,
    description = this.description,
    age = this.age,
    image = this.image
)

fun AuthorUpdateRequestDto.toAuthorUpdateRequest() = AuthorUpdateRequest(
    id = this.id,
    name = this.name,
    description = this.description,
    age = this.age,
    image = this.image
)

fun BookSummary.toBookEntity(author: AuthorEntity) = BookEntity(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    authorEntity = author
)

fun AuthorEntity.toAuthorSummaryDto(): AuthorSummaryDto {
    val authorId = this.id ?: throw InvalidAuthorException()
    checkNotNull(authorId)
    return AuthorSummaryDto(
        id = authorId,
        name = this.name,
        image = this.image
    )
}
fun AuthorSummaryDto.toAuthorSummary() = AuthorSummary(
    id = this.id,
    name = this.name,
    image = this.image

)
fun BookSummaryDto.toBookSummary() = BookSummary(
    isbn = this.isbn,
    title = this.title,
    image = this.image,
    description = this.description,
    authorSummary = this.author.toAuthorSummary()
)

fun BookEntity.toBookSummaryDto() = BookSummaryDto(
    isbn = this.isbn,
    title = this.title,
    description = this.description,
    image = this.image,
    author = this.authorEntity.toAuthorSummaryDto()
)

fun UserSignUpDto.toUserEntity() = UserEntity(
    userId = this.userId,
   userEmail = this.userEmail ,
    userPassword = this.userPassword,
    userName = this.userName
)

