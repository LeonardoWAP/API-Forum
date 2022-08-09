package com.ForumApi.controller.response

import java.time.LocalDateTime

data class ThreadResponse(
    var title: String,
    var description: String,
    var created_at: LocalDateTime,
    var message: List<MessageResponse>
        )