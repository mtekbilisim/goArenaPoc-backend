package com.mtek.poc.feed_service.model

import javax.persistence.*

@Entity
@Table(name="users",schema="users")
class UserModel(
    @Column
    val username: String? = "",
    @Column
    val avatar: String? = "",
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) val id: Long = 0,
) {

}