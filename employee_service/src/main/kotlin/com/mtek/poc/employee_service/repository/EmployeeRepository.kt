package com.mtek.poc.employee_service.repository
import com.mtek.poc.employee_service.model.EmployeeWithShopModel
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeRepository : JpaRepository<EmployeeWithShopModel, Long>