package com.mtek.poc.feed_service.model

import javax.persistence.*

@Entity
@Table(name = "media", schema = "feeds")
class Media(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column val uri: String? = "",
    @Column val mimeType: String? = "",
    @Column val feedId: Long? = 0,
    @Column val userId: Long? = 0
)