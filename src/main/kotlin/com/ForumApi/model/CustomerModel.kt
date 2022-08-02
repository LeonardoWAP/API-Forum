package com.ForumApi.model

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

data class CustomerModel (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id :Int? = null,

    @Column
    var user :String,

    @Column
    var email :String,

    @Column
    var password :String,

    @OneToMany
//    @JoinColumn ("thread_id")
    var threads : MutableList<ThreadModel>)