package com.mtek.poc.auth_service.model

import com.mtek.poc.auth_service.enums.EmployeeType

class Register(
    val email: String,
    val password: String,
    val firstName: String,
    val lastName: String,
    val avatar: String?,
    val userName: String,
    val userType: EmployeeType,
    val shopId:Long
) {
}