package com.nishchay.bookstore.service.impl

import com.nishchay.bookstore.domain.AuthorUpdateRequest
import com.nishchay.bookstore.domain.entity.AuthorEntity
import com.nishchay.bookstore.repositories.AuthorRepository
import com.nishchay.bookstore.service.AuthorService
import jakarta.transaction.Transactional
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class AuthorServiceImpl(private val authorRepository: AuthorRepository) : AuthorService {

    override fun create(authorEntity: AuthorEntity): AuthorEntity {
        require(null == authorEntity.id)
       return authorRepository.save(authorEntity)
    }

    override fun list(): List<AuthorEntity> {
        return authorRepository.findAll()
    }

    override fun listByPage(page: Int, size: Int): Page<AuthorEntity> {
        val pageable: Pageable = PageRequest.of(page,size)
        return authorRepository.findAll(pageable)
    }

    override fun get(id: Long): AuthorEntity? {
        return authorRepository.findByIdOrNull(id)
    }


    @Transactional
    override fun fullUpdate(id: Long, authorEntity: AuthorEntity): AuthorEntity {
        check(authorRepository.existsById(id))
        val normalisedAuthor = authorEntity.copy(id = id)
        return authorRepository.save(normalisedAuthor)

    }

    @Transactional
    override fun partialUpdate(id: Long, authorUpdate: AuthorUpdateRequest): AuthorEntity {
        val existingAuthor =authorRepository.findByIdOrNull(id)
        checkNotNull(existingAuthor)

         val updatedAuthor =existingAuthor.copy(
             name = authorUpdate.name ?: existingAuthor.name,
             age = authorUpdate.age ?: existingAuthor.age,
             description = authorUpdate.description ?: existingAuthor.description,
             image = authorUpdate.image ?: existingAuthor.image

         )

        return authorRepository.save(updatedAuthor)
    }

    override fun delete(id: Long) {
        authorRepository.deleteById(id)
    }
}