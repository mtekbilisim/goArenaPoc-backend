package com.mtek.poc.auth_service.controller

import com.mtek.poc.auth_service.configs.ResponseWrap
import com.mtek.poc.auth_service.model.AuthSuccess
import com.mtek.poc.auth_service.model.EmployeeModel
import com.mtek.poc.auth_service.model.Login
import com.mtek.poc.auth_service.model.Register
import com.mtek.poc.auth_service.repository.UserRepository
import com.mtek.poc.auth_service.service.Okta
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/"])
class Auth() {

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

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal user: OidcUser?): ResponseWrap<EmployeeModel> {
        var r: Jwt = SecurityContextHolder.getContext().authentication.principal as Jwt
        var employee = userRepository.findByEmail(r.claims.get("sub").toString())
        return ResponseWrap(employee)
    }

}