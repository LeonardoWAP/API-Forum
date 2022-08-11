package com.ForumApi.ModelBuilderHelper

import com.ForumApi.Enums.ThreadStatus
import com.ForumApi.model.CustomerModel
import com.ForumApi.model.HashtagModel
import com.ForumApi.model.MessageModel
import com.ForumApi.model.ThreadModel
import java.time.LocalDateTime
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String= "customer name",
    email: String="${UUID.randomUUID()}@email.com",
    password: String = "password"
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    password = password,
)

fun buildThread (
    id: Int? = null,
    customer: CustomerModel = buildCustomer(),
    status : ThreadStatus,
    title : String = "olá",
    description : String = "oiiiiii pessual",
    customerid : Int = 1,
    messages : List<MessageModel> = listOf(),
    hashtags : List<HashtagModel> = listOf(),
    createdAt : LocalDateTime = LocalDateTime.now()

)= ThreadModel(
    id = id,
    status = status,
    title = title,
    description = description,
    customerId = customerid,
    messages = messages,
    hashtags = hashtags,
    createdAt = createdAt
)