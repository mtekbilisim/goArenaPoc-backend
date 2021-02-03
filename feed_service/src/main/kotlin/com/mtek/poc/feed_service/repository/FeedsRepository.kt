package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.model.FeedPlainModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FeedsRepository : JpaRepository<FeedModel, Long>{
    fun findByTitleContainsOrderByIdDesc(title: String?) : List<FeedModel>
    fun findByTagsContainsOrderByIdDesc(tag: String?) : List<FeedModel>
    @Query( value = "select * from feeds.feeds f join users.users u on f.user_id = u.id where u.id = :user_id order by f.id desc", nativeQuery = true)
    fun findByUserInOrderByIdAsc (@Param("user_id") userId: Long?) : List<FeedModel>
}
interface FeedsPostRepository : JpaRepository<FeedPlainModel, Long>