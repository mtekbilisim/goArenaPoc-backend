package com.mtek.poc.dashboard_service.repository

import com.mtek.poc.dashboard_service.model.ExpectationModel
import com.mtek.poc.dashboard_service.model.SalesModel
import org.springframework.data.jpa.repository.JpaRepository

interface SalesRepository : JpaRepository<SalesModel, Long> {
    fun findAllByUserId(userId: Long): List<SalesModel>
    fun findAllByShopId(shopId: Long): List<SalesModel>
}