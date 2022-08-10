package com.ForumApi.controller.response

import com.ForumApi.model.HashtagModel

data class response(
    val titles : String,
    val hashtags :  List<HashtagModel>
)