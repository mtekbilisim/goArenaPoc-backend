package com.mtek.poc.user_service.model

import lombok.*
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("users.users")
open class UserModel(
        @Column
        var username: String?,
        @Column
        var password: String?,
        @Column
        var avatar: String?,
        @Column
        var email: String?,
        @Column
        var first_name: String?,
        @Column
        var last_name: String?,
        @Column
        var uuid: String?,
        @Id
        @Column
        var id: Long?
)