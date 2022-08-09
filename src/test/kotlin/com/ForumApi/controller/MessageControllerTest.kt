package com.ForumApi.controller

import com.ForumApi.controller.request.PostMessageRequest
import com.ForumApi.repository.MessageRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class MessageControllerTest {
    @Autowired
    private lateinit var mockMvc : MockMvc

    @Autowired
    private lateinit var MessageRepository : MessageRepository

    @Autowired
    private lateinit var objectMapper : ObjectMapper

    @BeforeEach
    fun setup() = MessageRepository.deleteAll()

    @AfterEach
    fun tearDown() = MessageRepository.deleteAll()



    @Test
    fun ` should create message ` (){
        val request = PostMessageRequest("teste testes teste",1)
        mockMvc.perform(
            MockMvcRequestBuilders.post("/message/create")
            .contentType(org.springframework.http.MediaType
                .APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
            .andExpect(MockMvcResultMatchers.status().isCreated)

        val message = MessageRepository.findAll().toList()
        Assertions.assertEquals(1, message.size)
        Assertions.assertEquals(request.description,message.first().description)
        Assertions.assertEquals(request.threadId,message.first().threadId)
    }
}