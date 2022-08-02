package com.ForumApi.model

import javax.persistence.*


data class CustomerModel (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var user :String,

    @Column
    var email :String,

    @Column
    var password :String
    )