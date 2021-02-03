package com.mtek.poc.auth_service.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.http.ResponseEntity
import com.fasterxml.jackson.databind.ObjectMapper
import com.mtek.poc.auth_service.model.*
import org.springframework.http.converter.FormHttpMessageConverter
import org.springframework.web.client.HttpClientErrorException
import org.springframework.util.LinkedMultiValueMap

import org.springframework.util.MultiValueMap





@Service
class Okta(
    @Value("\${okta.api.address}")
    val address: String,
    @Value("\${okta.token.address}")
    val tokenAddress: String,
    @Value("\${okta.token.clientId}")
    val clientId: String,
    @Value("\${okta.token.clientSecret}")
    val clientSecret: String,
    @Value("\${okta.api.ssws}")
    val ssws: String
) {
    fun signIn(firstName: String, lastName: String, email: String, password: String): ResponseEntity<String> {
        val headers: HttpHeaders = HttpHeaders()
        headers.add("Authorization", ssws)
        headers.setContentType(MediaType.APPLICATION_JSON);
        val profile = Profile(firstName, lastName, email, email)
        val credentialMap: MutableMap<String, String> = mutableMapOf()
        credentialMap.put("value", password)
        val credentials = Credentials(credentialMap)
        val jsonString = ObjectMapper().writeValueAsString(SignIn(profile, credentials))
        val entity = HttpEntity(jsonString, headers)
        val restTemplate = RestTemplate()
        try {
            return restTemplate.postForEntity(
                "$address/users",
                entity,
                String::class.java
            )
        } catch (e: HttpClientErrorException) {
            return ResponseEntity<String>(e.responseBodyAsString, e.responseHeaders, e.rawStatusCode)
        }
    }

    fun login(username: String, password: String): ResponseEntity<AuthSuccess> {
        val headers: HttpHeaders = HttpHeaders()
      //  headers.add("Authorization", ssws)
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        val optionsMap: MultiValueMap<String, String> = LinkedMultiValueMap()

        // val optionsMap: MutableMap<String, String> = mutableMapOf()
        optionsMap.add("client_id", clientId)
        optionsMap.add("client_secret",clientSecret)
        optionsMap.add("grant_type","password")
        optionsMap.add("scope","openid")
        optionsMap.add("username",username)
        optionsMap.add("password",password)
        val entity = HttpEntity<MultiValueMap<String, String>>(optionsMap, headers)
        val restTemplate = RestTemplate()
    //    restTemplate.messageConverters.add(FormHttpMessageConverter())
        try {
            return restTemplate.postForEntity(
                "$tokenAddress/v1/token",
                entity,
                AuthSuccess::class.java

            )
        } catch (e: HttpClientErrorException) {
            val result = AuthSuccess(null,null,null,null,e.responseBodyAsString);
            return ResponseEntity<AuthSuccess>(result, e.responseHeaders, e.rawStatusCode)
        }

    }

    fun logout(sessionId: String): ResponseEntity<String> {
        val restTemplate = RestTemplate()
        try {
            restTemplate.delete("$address/sessions/$sessionId")
            return ResponseEntity<String>("{\"status\":\"loggedout\"}", null, 200)
        } catch (e: HttpClientErrorException) {
            return ResponseEntity<String>(e.responseBodyAsString, e.responseHeaders, e.rawStatusCode)
        }
    }
}