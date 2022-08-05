package com.ForumApi.controller

import com.ForumApi.controller.request.PostMessageRequest
import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.controller.response.MessageResponse
import com.ForumApi.model.MessageModel
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.MessageRepository
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/message")
class MessageController(
    private val messageRepository: MessageRepository
) {
    private fun MessageModel.toResponse(): MessageResponse{
        return MessageResponse(
            description = this.description,
            created_at = this.createdAt
        )
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostMessageRequest){
        val message = MessageModel(
            description = request.description,
            customerId = 1,
            threadId = request.threadId
        )
        messageRepository.save(message)
    }
}