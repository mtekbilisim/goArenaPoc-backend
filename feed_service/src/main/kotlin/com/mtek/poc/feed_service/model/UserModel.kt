package com.mtek.poc.feed_service.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="users",schema="users")
class UserModel(
    @Column
    val username: String? = "",
    @Column
    val avatar: String? = "",
    @Id
    @Column
    val id: Long? = 0,
) {

}