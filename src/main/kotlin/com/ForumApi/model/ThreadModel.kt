package com.ForumApi.model

import com.ForumApi.Enums.ThreadStatus
import javax.persistence.*

@Entity(name = "thread")
data class ThreadModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var status : ThreadStatus,

    @Column
    var title : String,

    @Column
    var description : String,

    //@ManyToOne
    @JoinColumn(name = "customer_id")
    var customerId : Int,
    )
