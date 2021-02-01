package com.mtek.poc.employee_service.repository

import com.mtek.poc.employee_service.model.ShopWithEmployeesModel
import org.springframework.data.jpa.repository.JpaRepository

interface ShopRepository : JpaRepository<ShopWithEmployeesModel, Long>