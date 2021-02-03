package com.mtek.poc.auth_service.model

class AuthSuccess(
    var token_type: String?,
    var expires_in: Int?,
    var access_token: String?,
    var scope: String?,
    var error : String?
)