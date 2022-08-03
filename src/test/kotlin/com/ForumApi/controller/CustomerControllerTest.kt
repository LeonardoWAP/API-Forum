package com.ForumApi.controller

import com.ForumApi.controller.request.PostCustomerRequest
import com.ForumApi.repository.CustomerRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class CustomerControllerTest {
    @Autowired
    private lateinit var bCrypt: BCryptPasswordEncoder

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var objectMapper : ObjectMapper

    @BeforeEach
    fun setup() = customerRepository.deleteAll()

    @Test
    fun `should create customer`() {
        val request = PostCustomerRequest(
            "leonardo",
            "leonardo@email.com",
            "123"
        )
        mockMvc.perform(
            post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)
                )
        )
            .andExpect(status().isCreated)
        val customers = customerRepository.findAll().toList()
        assertEquals(1,customers.size)
        assertEquals(request.name,customers.first().name)
        assertEquals(request.email,customers.first().email)
        assertTrue(bCrypt.matches(request.password,customers.first().password))
    }
}