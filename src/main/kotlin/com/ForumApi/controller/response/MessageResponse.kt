package com.ForumApi.controller.response

import java.time.LocalDateTime

data class MessageResponse (
    var description : String,
    var created_at : LocalDateTime
        )