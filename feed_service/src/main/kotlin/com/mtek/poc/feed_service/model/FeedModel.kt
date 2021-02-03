package com.mtek.poc.feed_service.model

import com.mtek.poc.feed_service.enums.FeedStatus
import com.mtek.poc.feed_service.enums.FeedType
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "feeds", schema = "feeds")
class FeedModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var title: String,
    @Column
    var tags: String?,
    @Column
    @Enumerated(EnumType.STRING)
    var postType: FeedType = FeedType.TEXT,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId", referencedColumnName = "id")
    var comments: List<CommentModel>,
    @Column
    var postDate: LocalDateTime? = LocalDateTime.now(),
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId", referencedColumnName = "id")
    var medias: List<MediaModel>?,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    var user: UserModel,
    @Column
    @Enumerated(EnumType.STRING)
    var status: FeedStatus = FeedStatus.DRAFT
)

