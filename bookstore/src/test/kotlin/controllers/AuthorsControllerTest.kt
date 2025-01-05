package controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.nishchay.bookstore.domain.dto.AuthorDto
import com.nishchay.bookstore.domain.entity.AuthorEntity
import com.nishchay.bookstore.service.AuthorService
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.mockito.ArgumentMatchers.eq
import org.mockito.Mockito.verify
import org.springframework.beans.factory.annotation.Autowired


import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
class AuthorsControllerTest @Autowired constructor(
    private val mockMvc: MockMvc,
    @MockitoBean val authorService: AuthorService
) {

    val objectMapper = ObjectMapper()

    @Test
    fun `test that create Author returns a Http 201 status on a successful create`(){
       mockMvc.post("/v1/authors"){
           contentType = MediaType.APPLICATION_JSON
           accept = MediaType.APPLICATION_JSON
           content = objectMapper.writeValueAsString(
               AuthorDto(
                   id = null,
                   name = "John Doe",
                   age = 30,
                   image = "author-image.jpeg",
                   description = "some-description"
               )
           )
       }.andExpect {
           status {
               isCreated()
           }
       }
    }

    @Test
    fun `test that crate Author save the Author`() {
        mockMvc.post("/v1/authors"){
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(
                AuthorDto(
                    id = null,
                    name = "John Doe",
                    age = 30,
                    image = "author-image.jpeg",
                    description = "some-description"
                )
            )
        }

        val expected = AuthorEntity(
            id = null,
            name = "John Doe",
            age = 30,
            image = "author-image.jpeg",
            description = "some-description"
        )
        verify(authorService.create(eq(expected)))
    }

}