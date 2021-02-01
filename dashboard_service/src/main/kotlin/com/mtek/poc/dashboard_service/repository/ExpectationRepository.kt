package com.mtek.poc.dashboard_service.repository

import com.mtek.poc.dashboard_service.model.ExpectationModel
import org.springframework.data.jpa.repository.JpaRepository

interface ExpectationRepository : JpaRepository<ExpectationModel, Long>