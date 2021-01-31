package com.mtek.poc.file_service.service

import java.net.MalformedURLException

import org.springframework.core.io.UrlResource

import java.io.IOException

import java.nio.file.StandardCopyOption

import org.springframework.web.multipart.MultipartFile

import com.mtek.poc.file_service.config.FileStorageProperties
import com.mtek.poc.file_service.exception.FileNotFoundException
import com.mtek.poc.file_service.exception.FileStorageException

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths


@Service
class FileStorageService @Autowired constructor(fileStorageProperties: FileStorageProperties) {
    private val fileStorageLocation: Path
    fun storeFile(file: MultipartFile): String {
        // Normalize file name
        val fileName: String = StringUtils.cleanPath(file.originalFilename!!)
        return try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                throw FileStorageException("Sorry! Filename contains invalid path sequence $fileName")
            }

            // Copy file to the target location (Replacing existing file with the same name)
            val targetLocation: Path = fileStorageLocation.resolve(fileName)
            Files.copy(file.inputStream, targetLocation, StandardCopyOption.REPLACE_EXISTING)
            fileName
        } catch (ex: IOException) {
            throw FileStorageException("Could not store file $fileName. Please try again!", ex)
        }
    }

    fun loadFileAsResource(fileName: String): Resource {
        return try {
            val filePath: Path = fileStorageLocation.resolve(fileName).normalize()
            val resource: Resource = UrlResource(filePath.toUri())
            if (resource.exists()) {
                resource
            } else {
                throw FileNotFoundException("File not found $fileName")
            }
        } catch (ex: MalformedURLException) {
            throw FileNotFoundException("File not found $fileName", ex)
        }
    }

    init {
        fileStorageLocation = Paths.get(fileStorageProperties.uploadDirectory)
            .toAbsolutePath().normalize()
        try {
            Files.createDirectories(fileStorageLocation)
        } catch (ex: Exception) {
            throw FileStorageException("Could not create the directory where the uploaded files will be stored.", ex)
        }
    }
}