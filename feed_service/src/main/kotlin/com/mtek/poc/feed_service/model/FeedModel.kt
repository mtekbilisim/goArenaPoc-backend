package com.mtek.poc.feed_service.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "feeds", schema = "feeds")
class FeedModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column
    val title: String? = null,
    @Column
    val postType: FeedType? = FeedType.TEXT,
    @Column
    val likes: Int? = 0,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId", referencedColumnName = "id")
    val comments: List<CommentModel> = ArrayList<CommentModel>(),
    @Column
    val postDate: LocalDateTime? = null,
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "feedId", referencedColumnName = "id")
    val medias: List<Media>? = ArrayList<Media>(),
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    val userId: UserModel? = null,
    @Column
    val status:FeedStatus?=FeedStatus.DRAFT
)

enum class FeedType(val postType: String) {
    VIDEO("video"),
    IMAGE("image"),
    TEXT("text")
}

enum class FeedStatus( val status: String) {
    DRAFT("draft"),
    WAITING_APPROVAL("waiting_approval"),
    APPROVED ( "approved"),
    DECLINED ( "declined")
}