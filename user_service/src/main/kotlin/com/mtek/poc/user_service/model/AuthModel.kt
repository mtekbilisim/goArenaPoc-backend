package com.mtek.poc.user_service.model

import lombok.*

@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
class AuthModel(
    val access_token: String,
   // val user: UserModel
)