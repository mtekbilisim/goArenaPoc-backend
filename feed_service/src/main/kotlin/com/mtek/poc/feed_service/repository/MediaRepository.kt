package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.MediaModel
import org.springframework.data.jpa.repository.JpaRepository

interface MediaRepository: JpaRepository<MediaModel,Long> {
    fun findByFeedId(feedId: Long): List<MediaModel>
}