package com.ForumApi.controller

import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.controller.response.MessageResponse
import com.ForumApi.controller.response.PageResponse
import com.ForumApi.controller.response.ThreadIdResponse
import com.ForumApi.controller.response.ThreadResponse
import com.ForumApi.model.CustomerModel
import com.ForumApi.model.HashtagModel
import com.ForumApi.model.MessageModel
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.ThreadRepository
import com.ForumApi.security.UserCustomDetails
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.method.annotation.JsonViewResponseBodyAdvice

@RestController
@RequestMapping("/threads")
class ThreadController(
    private val threadRepository: ThreadRepository
){
    private fun ThreadModel.toResponse(): ThreadResponse{
        return ThreadResponse(
            created_at = this.createdAt,
            title = this.title,
            description = this.description,
            hashtags = this.hashtags,
            message = this.messages.map { it.toResponse() }
        )
    }
    private fun MessageModel.toResponse(): MessageResponse{
         return MessageResponse(
             description = this.description,
             createdAt = this.createdAt
         )
    }
    private fun <T> Page<T>.toPageResponse(): PageResponse<T>{
        return PageResponse(this.content , this.number , this.totalElements , this.totalPages)
    }



    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request : PostThreadRequest ): ThreadIdResponse? {
        val thread = ThreadModel(
            status = request.status,
            title = request.title,
            description = request.description,
            customerId = authenticatedCustomer().id!!,
            messages = emptyList(),
            hashtags = emptyList(),
        )
        threadRepository.save(thread)
        return ThreadIdResponse(id = thread.id)
    }


    @GetMapping("/listAll")
    fun findAll(@PageableDefault(page = 0 , size = 10) pageable :Pageable): PageResponse <String>{
        return threadRepository.findAll(pageable).map { it.title }.toPageResponse()
    }

    @GetMapping("listById/{id}")
    fun findById(@PathVariable id : Int): ThreadResponse {
        val thread = threadRepository.findByIdOrNull(id)
        if (thread != null){
            return thread.toResponse()
        }
        throw Exception("Not Found")
    }

    private fun authenticatedCustomer(): CustomerModel{
        return (SecurityContextHolder.getContext().authentication.principal as UserCustomDetails).customerModel
    }
}