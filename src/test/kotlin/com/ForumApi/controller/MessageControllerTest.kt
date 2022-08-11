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
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MockMvcBuilder
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.nio.charset.StandardCharsets


@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MessageControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var webApplicationContext: WebApplicationContext

    @Autowired
    private lateinit var messageRepository: MessageRepository

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
        messageRepository.deleteAll()
        customerRepository.deleteAll()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build()
    }

    @AfterEach
    fun tearDown() = messageRepository.deleteAll()


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

        val (status, response) = post("/message/create", request)


        Assertions.assertEquals(HttpStatus.CREATED, status)
    }

    private fun post(path: String, body: PostMessageRequest): Pair<HttpStatus, String> {
        val response = mockMvc.post(path){
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(body)
        }.andReturn()

        val contentAsString = response.response.getContentAsString(StandardCharsets.UTF_8)

        return Pair(HttpStatus.valueOf(response.response.status), contentAsString)
    }

}