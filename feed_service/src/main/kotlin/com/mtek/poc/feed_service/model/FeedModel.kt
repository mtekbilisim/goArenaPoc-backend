package com.mtek.poc.feed_service.model

import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("feeds.feeds")
class FeedModel(
    @Id
    @Column
    var id: Long
)