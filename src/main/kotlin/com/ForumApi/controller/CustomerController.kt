package com.ForumApi.Controller

import com.ForumApi.model.CustomerModel
import com.ForumApi.service.CustomerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/customer")
class CustomerController(
    val customerService : CustomerService
) {

}