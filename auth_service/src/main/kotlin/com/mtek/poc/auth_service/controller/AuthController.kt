package com.mtek.poc.auth_service.controller

import com.mtek.poc.auth_service.configs.ResponseWrap
import com.mtek.poc.auth_service.model.AuthSuccess
import com.mtek.poc.auth_service.model.EmployeeModel
import com.mtek.poc.auth_service.model.Login
import com.mtek.poc.auth_service.model.Register
import com.mtek.poc.auth_service.repository.UserRepository
import com.mtek.poc.auth_service.service.Okta
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping(value = ["/"])
class AuthController() {

    @Autowired
    lateinit var okta: Okta

    @Autowired
    lateinit var userRepository: UserRepository

    @PostMapping("/token")
    fun auth(@RequestBody login: Login): ResponseEntity<AuthSuccess> {
        return okta.login(login.email, login.password)
    }

    @PostMapping("/signin")
    fun signin(@RequestBody register: Register): ResponseWrap<EmployeeModel> {
        var result = okta.signIn(register.firstName, register.lastName, register.email, register.password)
        if(result.statusCode != HttpStatus.OK)
            throw ResourceNotFoundException("Bu kullanıcı daha önce kaydedilmiş ya da şifre geçersiz.")

        var employee: EmployeeModel = EmployeeModel(
            register.firstName,
            0,
            register.lastName,
            register.email,
            register.avatar,
            register.password,
            register.userName,
            register.userType,
            register.shopId
        )
        if (result.statusCode == HttpStatus.OK) {
            userRepository.save(employee)
        }

        return ResponseWrap(employee);
    }

    //    @GetMapping("/logout")
//    fun logout(@RequestParam(name = "session_id", required = true) session_id: String): ResponseEntity<String> {
//        return okta.logout(session_id)
//    }
//
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/me")
    fun me(): ResponseWrap<EmployeeModel> {
        var r: Jwt = SecurityContextHolder.getContext().authentication.principal as Jwt
        try {
            var employee = userRepository.findFirstByEmail(r.claims.get("sub").toString())
            return ResponseWrap(employee)
        } catch(e:Exception){
            throw ResponseStatusException(HttpStatus.NOT_FOUND,"Kullanıcı kaydı bulunmuyor")
        }
    }

}