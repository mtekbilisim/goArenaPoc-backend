package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.CommentModel
import com.mtek.poc.feed_service.model.LikeModel
import com.mtek.poc.feed_service.model.LikePlainModel
import org.springframework.data.jpa.repository.JpaRepository

interface LikeRepository : JpaRepository<LikeModel, Long>{
    fun findByFeedId(feedId: Long): List<LikeModel>
}
interface LikePostRepository : JpaRepository<LikePlainModel, Long>