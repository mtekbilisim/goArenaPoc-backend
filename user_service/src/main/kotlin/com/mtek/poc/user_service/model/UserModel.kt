package com.mtek.poc.user_service.model

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.*


@Entity
@Table(name="users",schema = "users")
class UserModel(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) val id: Long=0,
    @Column var uuid: String="", @Column var last_name: String="",
    @Column var first_name: String="", @Column var email: String="",
    @Column var avatar: String="", @Column var password: String="",
    @Column var username: String=""
)