package com.ForumApi.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping("/health")
    fun helloWorld(): String{
        return "comunicação funcionando"
    }
}