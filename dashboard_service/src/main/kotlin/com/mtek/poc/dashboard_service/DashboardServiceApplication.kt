package com.mtek.poc.dashboard_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EnableEurekaClient
@EnableJpaRepositories
class DashboardServiceApplication

fun main(args: Array<String>) {
    runApplication<DashboardServiceApplication>(*args)
}
