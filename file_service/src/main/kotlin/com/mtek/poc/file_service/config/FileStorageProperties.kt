package com.mtek.poc.file_service.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "file")
class FileStorageProperties(
) {
    var uploadDirectory: String? = null
}