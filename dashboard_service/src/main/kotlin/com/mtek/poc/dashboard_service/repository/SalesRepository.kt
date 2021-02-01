package com.mtek.poc.dashboard_service.repository

import com.mtek.poc.dashboard_service.model.SalesModel
import org.springframework.data.jpa.repository.JpaRepository

interface SalesRepository : JpaRepository<SalesModel, Long> {
}