package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.CommentModel
import com.mtek.poc.feed_service.model.CommentPlainModel
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<CommentModel, Long>{
    fun findByFeedId(feedId: Long): List<CommentModel>
}
interface CommentPostRepository : JpaRepository<CommentPlainModel, Long>