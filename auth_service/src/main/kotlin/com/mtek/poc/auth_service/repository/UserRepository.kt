package com.mtek.poc.auth_service.repository

import com.mtek.poc.auth_service.model.EmployeeModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<EmployeeModel, Long> {
    fun findFirstByEmail(email: String) : EmployeeModel
}