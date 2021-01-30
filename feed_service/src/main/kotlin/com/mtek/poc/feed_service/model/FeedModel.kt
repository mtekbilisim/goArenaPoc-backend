package com.mtek.poc.feed_service.model

import lombok.*
import javax.persistence.*

@Entity
@Table(name="feeds", schema="feeds")
class FeedModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long=0,

)