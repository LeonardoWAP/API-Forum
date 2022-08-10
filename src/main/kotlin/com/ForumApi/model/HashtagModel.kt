package com.ForumApi.model

import com.ForumApi.controller.ThreadController
import javax.persistence.*

@Entity(name ="hashtag")
data class HashtagModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,

    @Column
    var name: String ,

//    @ManyToMany(mappedBy = "hashtags")
//    var thread:  List<ThreadModel>
   // var thread : List<ThreadModel>
)