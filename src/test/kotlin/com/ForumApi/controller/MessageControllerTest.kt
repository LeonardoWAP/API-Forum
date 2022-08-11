package com.ForumApi.controller

import com.ForumApi.Enums.ThreadStatus
import com.ForumApi.controller.request.PostCustomerRequest
import com.ForumApi.controller.request.PostMessageRequest
import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.model.CustomerModel
import com.ForumApi.model.HashtagModel
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.CustomerRepository
import com.ForumApi.repository.MessageRepository
import com.ForumApi.repository.ThreadRepository
import com.ForumApi.security.JwtUtil
import com.ForumApi.security.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class MessageControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var MessageRepository: MessageRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @Autowired
    private lateinit var threadRepository: ThreadRepository

    @Autowired
    private lateinit var jwtUtil : JwtUtil

    @BeforeEach
    fun setup() {
        MessageRepository.deleteAll()
        customerRepository.deleteAll()
    }

    @AfterEach
    fun tearDown() = MessageRepository.deleteAll()


    @Test
    fun ` should create message `() {
        val customer = customerRepository.save(CustomerModel(id = 1, "leonardo", email = "leonardo@email.com", password = "1234"))

        val token = jwtUtil.generateToken(customer.id!!)



        val thread = threadRepository.save(
            ThreadModel(
                status = ThreadStatus.ABERTO,
                title = "TITULO TESTE",
                description = "TESTES",
                customerId = 1,
                messages = emptyList(),
                hashtags = listOf(
                    HashtagModel(name = "teste1"),
                    HashtagModel(name = "teste2")
                )
            )
        )


        val request = PostMessageRequest(
            description = "description",
            threadId = thread.id!!
        )

        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/message/create")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization","Bearer $token")
        )
            .andExpect(MockMvcResultMatchers.status().`is`(201))
            .andReturn()
    }

}