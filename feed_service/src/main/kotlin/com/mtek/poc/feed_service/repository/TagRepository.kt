package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.TagModel
import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<TagModel, Long> {
    fun findAllTop5ByOrderByPopularityAsc(): List<TagModel>
}