package com.ForumApi.repository

import com.ForumApi.model.MessageModel
import org.springframework.data.jpa.repository.JpaRepository

interface MessageRepository : JpaRepository<MessageModel,Int> {
}