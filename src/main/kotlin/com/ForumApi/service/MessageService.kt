package com.ForumApi.service

import com.ForumApi.repository.MessageRepository
import org.springframework.stereotype.Service

@Service
class MessageService (val messageRepository: MessageRepository) {

}
