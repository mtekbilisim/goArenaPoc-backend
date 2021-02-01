package com.mtek.poc.employee_service

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "EMPLOYEE & SHOP API",
        description = "" +
                "Turkcell GoArena employees and shops API",
        contact = Contact(name = "Emrah TOY", url = "https://www.mtekbilisim.com", email = "emrah.toy@mtekbilisim.com"),
        license = License(name = "MIT Licence", url = "https://www.wikiwand.com/en/MIT_License")
    ), servers = [Server(url = "http://localhost:8080/employees")]
)
@EnableEurekaClient
@EnableJpaRepositories

class EmployeeServiceApplication

fun main(args: Array<String>) {
    runApplication<EmployeeServiceApplication>(*args)
}


