package com.mtek.poc.dashboard_service.repository

import com.mtek.poc.dashboard_service.model.ExpectationModel
import org.springframework.data.jpa.repository.JpaRepository

interface ExpectationRepository : JpaRepository<ExpectationModel, Long>{
    fun findAllByUserId(userId:Long?) : List<ExpectationModel>
    fun findAllByShopId(shopId:Long?) : List<ExpectationModel>
}