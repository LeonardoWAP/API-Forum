package com.ForumApi.service

import com.ForumApi.repository.ThreadRepository
import org.springframework.stereotype.Service

@Service
class ThreadService(val threadRepository: ThreadRepository) {
}