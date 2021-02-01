package com.mtek.poc.feed_service.model

import com.mtek.poc.feed_service.enums.FeedStatus
import com.mtek.poc.feed_service.enums.FeedType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "feeds", schema = "feeds")
class FeedPlainModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var title: String,
    @Column
    @Enumerated(EnumType.STRING)
    var postType: FeedType = FeedType.TEXT,
    @Column
    var likes: Int? = 0,

    @Column
    var postDate: LocalDateTime? = LocalDateTime.now(),

    @Column
    var userId: Long,

    @Column
    @Enumerated(EnumType.STRING)
    var status: FeedStatus = FeedStatus.DRAFT
)

