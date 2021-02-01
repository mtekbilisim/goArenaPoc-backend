package com.mtek.poc.feed_service.model

import javax.persistence.*

@Entity
@Table(name = "users", schema = "users")
class UserModel(
    @Column
    var username: String,
    @Column
    var avatar: String?,
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long,
) {

}