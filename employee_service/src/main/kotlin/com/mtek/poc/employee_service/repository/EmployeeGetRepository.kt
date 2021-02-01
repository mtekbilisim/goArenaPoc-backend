package com.mtek.poc.employee_service.repository
import com.mtek.poc.employee_service.model.EmployeeModel
import com.mtek.poc.employee_service.model.EmployeeWithShopModel
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeGetRepository : JpaRepository<EmployeeWithShopModel, Long>
interface EmployeePostRepository : JpaRepository<EmployeeModel, Long>