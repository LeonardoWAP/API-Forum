package com.ForumApi.model

import com.ForumApi.Enums.ThreadStatus
import java.time.LocalDateTime
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

    @OneToMany(mappedBy = "thread")
    var messages : List<MessageModel>,

    @ManyToMany(cascade = arrayOf(CascadeType.ALL))
    @JoinTable(
        name = "hashtag_thread",
        joinColumns = arrayOf(JoinColumn(name = "thread_id")),
        inverseJoinColumns = arrayOf(JoinColumn(name = "hashtag_id"))
    )
    var hashtags : List<HashtagModel>,


    @Column(name = "created_at")
    val createdAt : LocalDateTime = LocalDateTime.now()
    )
