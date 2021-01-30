package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.FeedModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FeedsRepository : JpaRepository<FeedModel, Long> {
}