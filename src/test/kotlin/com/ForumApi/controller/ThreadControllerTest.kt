package com.ForumApi.controller

import com.ForumApi.Enums.ThreadStatus
import com.ForumApi.controller.request.PostCustomerRequest
import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.model.CustomerModel
import com.ForumApi.model.HashtagModel
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.CustomerRepository
import com.ForumApi.repository.HashtagRepository
import com.ForumApi.repository.ThreadRepository
import com.ForumApi.security.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.json.JSONObject
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
class ThreadControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var threadRepository: ThreadRepository

    @Autowired
    private lateinit var hashtagRepository: HashtagRepository

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() {
        threadRepository.deleteAll()
        customerRepository.deleteAll()
    }


    @Test
    fun `should create thread`() {
        val requestCustomer = PostCustomerRequest(
            "leonardo",
            "leonardo@email.com",
            "123"
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/customers/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCustomer))
        )

        val requestLogin = LoginRequest(
            "leonardo@email.com",
            "123"
        )

        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestLogin))
        ).andReturn()
        val authorization = response.response.getHeader("Authorization")


        val requestThread = PostThreadRequest(
            "oi",
            ThreadStatus.ABERTO,
            "oi",
            listOf("#java", "#ajuda", "#socorro", "#consegui", "#hashtag")
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/threads/create")
                .header("authorization", "Bearer $authorization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestThread))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
        val threads = threadRepository.findAll().toList()
        Assertions.assertEquals(1, threads.size)
        Assertions.assertEquals(requestThread.status, threads.first().status)
        Assertions.assertEquals(requestThread.description, threads.first().description)
        Assertions.assertEquals(requestThread.title, threads.first().title)
    }

    @Test
    fun ` should return title of all threads`() {
        val requestCustomer = PostCustomerRequest(
            "leonardo",
            "leonardo@email.com",
            "123"
        )
        mockMvc.perform(
            MockMvcRequestBuilders.post("/customers/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestCustomer))
        )

        val requestLogin = LoginRequest(
            "leonardo@email.com",
            "123"
        )

        val response = mockMvc.perform(
            MockMvcRequestBuilders.post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestLogin))
        ).andReturn()
        val authorization = response.response.getHeader("Authorization")


        val requestThread = PostThreadRequest(
            "oi",
            ThreadStatus.ABERTO,
            "oi",
            listOf("#java", "#ajuda", "#socorro", "#consegui", "#hashtag")
        )

        mockMvc.perform(
            MockMvcRequestBuilders.post("/threads/create")
                .header("authorization", "Bearer $authorization")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestThread))
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)

        mockMvc.perform(MockMvcRequestBuilders.get("/threads/listAll"))
            .andExpect(MockMvcResultMatchers.status().`is`(200))

        val threads = threadRepository.findAll().toList()
        Assertions.assertEquals(1, threads.size)
        Assertions.assertEquals(requestThread.title, threads.first().title)
        Assertions.assertEquals(requestThread.status, threads.first().status)
        Assertions.assertEquals(requestThread.description, threads.first().description)


    }

//    @Test
//
//    fun `should return only the thread that was chosen`() {
//
//
//
////        val message = MessageModel(1,"teste message",1,)
//
//        val customer = CustomerModel(id = 1, "leonardo", email = "leonardo@email.com", password = "1234")
//
//        customerRepository.save(customer)
//
//        val thread = threadRepository.save(
//            ThreadModel(
//                status = ThreadStatus.ABERTO,
//                title = "TITULO TESTE",
//                description = "TESTES",
//                customerId = 1,
//                messages = emptyList(),
//                hashtags = listOf(
//                    HashtagModel(name = "teste1"),
//                    HashtagModel(name = "teste2")
//                ),
//                customerName = "leonardo"
//            )
//        )
//
//        val response = mockMvc.perform(MockMvcRequestBuilders.get("/threads/listById/${thread.id}"))
//            .andExpect(MockMvcResultMatchers.status().`is`(200))
//            .andReturn()
//
//        var threadResult = JSONObject(response.response.contentAsString)
////        var hashtagsResult = threadResult.getJSONArray("hashtags")
//        Assertions.assertEquals("TESTES", threadResult.getString("description"))
//        Assertions.assertEquals("TITULO TESTE", threadResult.getString("title"))
////        Assertions.assertEquals("teste1", hashtagsResult.getJSONObject(0).getString("name"))
////        Assertions.assertEquals("teste2", hashtagsResult.getJSONObject(1).getString("name"))
//    }
}