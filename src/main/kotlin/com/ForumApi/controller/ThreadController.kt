package com.ForumApi.controller

import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.ThreadRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/threads")
class ThreadController(
    private val threadRepository: ThreadRepository
){


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostThreadRequest){
        val thread = ThreadModel(
            status = request.status,
            title = request.title,
            description = request.description,
            customerId = 1
        )
        threadRepository.save(thread)
    }
}