package com.ForumApi.model

import com.ForumApi.Enums.ThreadStatus
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "message")
data class MessageModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var description : String,

    @JoinColumn(name = "customer_id")
    var customerId : Int,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "thread_id", nullable = false)
    var thread : ThreadModel,


    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now()
)