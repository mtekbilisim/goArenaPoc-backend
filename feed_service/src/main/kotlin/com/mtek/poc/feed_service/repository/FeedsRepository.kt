package com.mtek.poc.feed_service.repository

import com.mtek.poc.feed_service.model.FeedModel
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface FeedsRepository : ReactiveCrudRepository<FeedModel, Long> {
}