package com.ForumApi.controller.response

import com.ForumApi.model.HashtagModel
import java.time.LocalDateTime

data class ThreadResponse(
    var title: String,
    var description: String,
    var hashtags : List<HashtagModel>,
    var created_at: LocalDateTime,
    var message: List<MessageResponse>
        )