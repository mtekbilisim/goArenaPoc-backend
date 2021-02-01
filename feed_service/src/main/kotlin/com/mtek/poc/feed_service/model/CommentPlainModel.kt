package com.mtek.poc.feed_service.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comments", schema = "feeds")
class CommentPlainModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var comment: String?,
    @Column
    var postDate: LocalDateTime? = LocalDateTime.now(),
    @Column
    var userId: Long,
    @Column
    var feedId: Long
) {

}