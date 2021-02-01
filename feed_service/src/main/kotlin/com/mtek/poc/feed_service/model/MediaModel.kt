package com.mtek.poc.feed_service.model

import javax.persistence.*

@Entity
@Table(name = "media", schema = "feeds")
class MediaModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column var uri: String,
    @Column var mimeType: String?,
    @Column var feedId: Long,
    @Column var userId: Long
)