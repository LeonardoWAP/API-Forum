package com.ForumApi.controller.request

import com.ForumApi.Enums.ThreadStatus
import javax.persistence.*

data class PostThreadRequest (
    var title : String,

    var status : ThreadStatus,

    var description : String,

    var hashtags : List<String>
    )