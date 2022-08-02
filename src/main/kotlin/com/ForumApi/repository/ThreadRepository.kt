package com.ForumApi.repository

import com.ForumApi.model.ThreadModel
import org.springframework.data.jpa.repository.JpaRepository

interface ThreadRepository : JpaRepository <ThreadModel, Int>{

}
