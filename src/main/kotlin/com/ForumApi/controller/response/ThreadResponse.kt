package com.ForumApi.controller.response

import com.ForumApi.Enums.ThreadStatus
import java.time.LocalDateTime
import javax.persistence.*

data class ThreadResponse (
    var title : String,
    var description : String,
    var created_at : LocalDateTime
        )