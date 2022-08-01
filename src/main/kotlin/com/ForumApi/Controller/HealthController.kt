package com.ForumApi.Controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthController {
    @GetMapping
    fun helloWorld(): String{
        return "comunicação funcionando"
    }
}