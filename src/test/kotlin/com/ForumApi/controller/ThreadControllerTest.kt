package com.ForumApi.controller

import com.ForumApi.Enums.ThreadStatus
import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.repository.CustomerRepository
import com.ForumApi.repository.ThreadRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import kotlin.concurrent.thread

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
class ThreadControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var threadRepository: ThreadRepository

    @Autowired
    private lateinit var objectMapper : ObjectMapper

    @BeforeEach
    fun setup() = threadRepository .deleteAll()

    @Test
    fun `should create thread`(){
        val request = PostThreadRequest(
            "oi",
            ThreadStatus.ABERTO,
            "oi"
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/threads/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)
                )
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
        val threads = threadRepository.findAll().toList()
        Assertions.assertEquals(1, threads.size)
        Assertions.assertEquals(request.status, threads.first().status)
        Assertions.assertEquals(request.description, threads.first().description)
        Assertions.assertEquals(request.title, threads.first().title)
    }

    @Test
    fun ` should return title of all threads `(){
        mockMvc.perform(MockMvcRequestBuilders.get("/threads/listAll"))
            .andExpect(MockMvcResultMatchers.status().`is`(200))

    }
}

