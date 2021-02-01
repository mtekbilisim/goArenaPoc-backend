package com.mtek.poc.feed_service.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "likes", schema = "feeds")
class LikeModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var postDate: LocalDateTime? = LocalDateTime.now(),
    @Column
    var feedId: Long,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    var user: UserModel,
)