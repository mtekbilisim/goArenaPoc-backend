package com.mtek.poc.feed_service.model

import javax.persistence.*

@Entity
@Table(name="tags",schema = "feeds")
class TagModel(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
    @Column
    var tag: String,
    var popularity: Long = 0
)
