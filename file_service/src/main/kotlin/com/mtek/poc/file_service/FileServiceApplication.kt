package com.mtek.poc.file_service

import com.mtek.poc.file_service.config.FileStorageProperties
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import io.swagger.v3.oas.annotations.servers.Server
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@OpenAPIDefinition(
    info = Info(
        title = "FILE SERVICE API",
        description = "" +
                "Turkcell GoArena file service API",
        contact = Contact(name = "Emrah TOY", url = "https://www.mtekbilisim.com", email = "emrah.toy@mtekbilisim.com"),
        license = License(name = "MIT Licence", url = "https://www.wikiwand.com/en/MIT_License")
    ), servers = [Server(url = "http://localhost:8080/files")]
)
@EnableEurekaClient
@EnableConfigurationProperties(FileStorageProperties::class)
class FileServiceApplication

fun main(args: Array<String>) {
    runApplication<FileServiceApplication>(*args)
}
