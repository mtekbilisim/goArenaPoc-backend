package com.mtek.poc.file_service.model

class UploadFileResponseModel(
     val fileName: String,
     val fileDownloadUri: String,
     val fileType: String?,
     val size: Long
)