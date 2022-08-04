package com.ForumApi.controller

import com.ForumApi.controller.request.PostThreadRequest
import com.ForumApi.controller.response.PageResponse
import com.ForumApi.controller.response.ThreadResponse
import com.ForumApi.model.ThreadModel
import com.ForumApi.repository.ThreadRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/threads")
class ThreadController(
    private val threadRepository: ThreadRepository
){
    private fun ThreadModel.toResponse(): ThreadResponse{
        return ThreadResponse(
            status = this.status,
            title = this.title,
            description = this.description,
            customerId = 1
        )
    }
    private fun <T> Page<T>.toPageResponse(): PageResponse<T>{
        return PageResponse(this.content , this.number , this.totalElements , this.totalPages)
    }

    @PostMapping("/create")
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

    @GetMapping("/listAll")
    fun findAll(@PageableDefault(page = 0 , size = 10) pageable :Pageable): PageResponse <ThreadResponse>{
        return threadRepository.findAll(pageable).map { it.toResponse() }.toPageResponse()
    }
}