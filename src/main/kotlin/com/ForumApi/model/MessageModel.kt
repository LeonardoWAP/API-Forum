package com.ForumApi.model

import javax.persistence.*

data class MessageModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var customerId : Int,

    @Column
    var description : String){

}