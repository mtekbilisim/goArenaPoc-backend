package com.mtek.poc.feed_service.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "comments", schema = "feeds")
class CommentModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
    @Column
    val comment: String? = null,
    @Column
    val postDate: LocalDateTime? = null,
    @Column
    val userId: Long? = null,
    @Column
    val feedId: Long? = null
) {

}