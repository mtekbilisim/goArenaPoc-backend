package com.mtek.poc.dashboard_service.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

class ChartModel(
    @Column
    var expectation: Long = 0,
    @Column
    var sales: Long = 0,
    @Column
    var productGroup: String? = null
)