package com.ForumApi.controller

import com.ForumApi.controller.request.PostMessageRequest
import com.ForumApi.controller.response.MessageResponse
import com.ForumApi.model.CustomerModel
import com.ForumApi.model.MessageModel
import com.ForumApi.repository.MessageRepository
import com.ForumApi.repository.ThreadRepository
import com.ForumApi.security.UserCustomDetails
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/message")
class MessageController(
    private val messageRepository: MessageRepository,
    private val threadRepository: ThreadRepository
) {
    private fun MessageModel.toResponse(): MessageResponse{
        return MessageResponse(
            description = this.description,
            createdAt = this.createdAt
        )
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostMessageRequest){
        val thread = threadRepository.findByIdOrNull(request.threadId)
        if (thread != null){
            val message = MessageModel(
                description = request.description,
                customerId = authenticatedCustomer().id!!,
                thread = thread
            )
            messageRepository.save(message)
        }else{
            throw Exception("Thread Not Found")
        }
    }
    private fun authenticatedCustomer(): CustomerModel {
        return (SecurityContextHolder.getContext().authentication.principal as UserCustomDetails).customerModel
    }
}