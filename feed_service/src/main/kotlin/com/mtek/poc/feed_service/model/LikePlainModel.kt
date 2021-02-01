package com.mtek.poc.feed_service.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "likes", schema = "feeds")
class LikePlainModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var comment: String,
    @Column
    var postDate: LocalDateTime? = LocalDateTime.now(),
    @Column
    var userId: Long,
    @Column
    var feedId: Long,
)