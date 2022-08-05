package com.ForumApi.controller.request

import com.ForumApi.Enums.ThreadStatus
import javax.persistence.*

data class PostMessageRequest (
    var description : String,
    var threadId : Int
        )