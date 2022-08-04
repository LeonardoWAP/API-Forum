package com.ForumApi.controller


import com.ForumApi.controller.request.PostCustomerRequest
import com.ForumApi.model.CustomerModel
import com.ForumApi.repository.CustomerRepository

import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customers")
class CustomerController (
    private val customerRepository: CustomerRepository,
    private val bCrypt: BCryptPasswordEncoder
    ) {
    private fun PostCustomerRequest.toCustomerModel(): CustomerModel {
        return CustomerModel(name = this.name, email = this.email,  password = this.password)
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody customer: PostCustomerRequest) {
        val customerCopy = customer.copy(
            password = bCrypt.encode(customer.password)
        )
        customerRepository.save(customerCopy.toCustomerModel())
    }
}