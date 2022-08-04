package com.ForumApi.controller.response

import com.ForumApi.Enums.ThreadStatus
import javax.persistence.*

data class ThreadResponse (
    var id :Int? = null,
    var status : ThreadStatus,
    var title : String,
    var description : String,
    var customerId : Int,
        )