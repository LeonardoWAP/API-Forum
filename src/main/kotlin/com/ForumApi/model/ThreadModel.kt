package com.ForumApi.model

import com.ForumApi.enums.ThreadStatus
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class ThreadModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var status : ThreadStatus ,

    @Column
    var title : String ,

    @Column
    var description : String ,

//    var hashtag : ThreadHashtagModel,

    @Column
    var customerId : Int ,

    var messages : MutableList<MessageModel>

    )
