package com.mtek.poc.auth_service.controller

import com.mtek.poc.auth_service.model.AuthSuccess
import com.mtek.poc.auth_service.model.Login
import com.mtek.poc.auth_service.model.Register
import com.mtek.poc.auth_service.service.Okta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/"])
class Auth() {

    @Autowired
    lateinit var okta: Okta

    @PostMapping("/token")
    fun auth(@RequestBody login: Login): ResponseEntity<AuthSuccess> {
        return okta.login(login.email, login.password)
    }

    @PostMapping("/signin")
    fun signin(@RequestBody register: Register): ResponseEntity<String> {
        return okta.signIn(register.firstName, register.lastName, register.email, register.password)
    }

//    @GetMapping("/logout")
//    fun logout(@RequestParam(name = "session_id", required = true) session_id: String): ResponseEntity<String> {
//        return okta.logout(session_id)
//    }
//
//    @GetMapping("/token_redirect")
//    fun test(): String {
//        return "TEST"
//    }

}