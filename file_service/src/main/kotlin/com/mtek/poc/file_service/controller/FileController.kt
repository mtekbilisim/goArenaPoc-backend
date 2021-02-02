package com.mtek.poc.file_service.controller

import com.mtek.poc.file_service.model.UploadFileResponseModel
import com.mtek.poc.file_service.service.FileStorageService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.ResponseEntity
import java.io.IOException
import javax.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import java.net.InetAddress

@RestController
class FileController {
    @Autowired
    private val environment: Environment? = null
    @Autowired
    private val fileStorageService: FileStorageService? = null

    @PostMapping("/uploadFile")
    fun uploadFile(@RequestParam("file") file: MultipartFile): UploadFileResponseModel {
        val fileName: String = fileStorageService!!.storeFile(file)
        val hostname: String = InetAddress.getLoopbackAddress().hostName
        var host: String = "http://"
        if (hostname.equals("localhost")) {
            host += "localhost:8080"
        } else {
            host += "turkcell.mtek.me:8080"
        }
        val fileDownloadUri = "$host/downloadFile/$fileName"
        return UploadFileResponseModel(
            fileName, fileDownloadUri,
            file.contentType, file.size
        )
    }

    @PostMapping("/uploadMultipleFiles")
    fun uploadMultipleFiles(@RequestParam("files") files: Array<MultipartFile>): List<UploadFileResponseModel> {
        return files.map { uploadFile(it) }
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    fun downloadFile(@PathVariable fileName: String, request: HttpServletRequest): ResponseEntity<Resource> {
        // Load file as Resource
        val resource: Resource = fileStorageService!!.loadFileAsResource(fileName)

        // Try to determine file's content type
        var contentType: String? = null
        try {
            contentType = request.servletContext.getMimeType(resource.getFile().getAbsolutePath())
        } catch (ex: IOException) {
            logger.info("Could not determine file type.")
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream"
        }
        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(
                HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + resource.getFilename().toString() + "\""
            )
            .body<Resource>(resource)
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(FileController::class.java)
    }
}