package com.ForumApi.repository


import com.ForumApi.model.CustomerModel
import com.ForumApi.model.HashtagModel
import org.springframework.data.repository.CrudRepository

interface HashtagRepository : CrudRepository <HashtagModel, Int>{
    fun findByName(name: String) : HashtagModel?
}