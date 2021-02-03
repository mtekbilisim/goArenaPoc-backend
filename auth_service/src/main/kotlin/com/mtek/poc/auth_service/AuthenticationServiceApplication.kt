package com.mtek.poc.auth_service

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
        title = "AUTH SERVICE API",
        description = "" +
                "Turkcell GoArena authentication service API",
        contact = Contact(name = "Emrah TOY", url = "https://www.mtekbilisim.com", email = "emrah.toy@mtekbilisim.com"),
        license = License(name = "MIT Licence", url = "https://www.wikiwand.com/en/MIT_License")
    ), servers = [Server(url = "http://turkcell.mtek.me:8080/authentication"),Server(url = "http://localhost:8080/authentication")]
)
@EnableEurekaClient
@EnableJpaRepositories
class AuthenticationServiceApplication

fun main(args: Array<String>) {
    runApplication<AuthenticationServiceApplication>(*args)
}
