package com.ForumApi.repository

import com.ForumApi.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository <CustomerModel, Int>{
    fun findByEmail(email: String) : CustomerModel?
}