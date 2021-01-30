package com.mtek.poc.user_service.controller

//import com.mtek.poc.user_service.configs.KeycloakClientConfig
import com.mtek.poc.user_service.model.AuthModel
import com.mtek.poc.user_service.model.UserModel
import com.mtek.poc.user_service.repository.UserRepository
//import org.keycloak.admin.client.Keycloak
//import org.keycloak.representations.AccessTokenResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = ["/"])
class AuthController(
    private val userRepository: UserRepository,
  //  private val keycloakClientConfig: KeycloakClientConfig
) {
    @PostMapping("/auth")
    fun auth(@RequestBody userModel: UserModel): AuthModel {
 //       val k: Keycloak = keycloakClientConfig.keycloakAuth(userModel.username, userModel.password);
   //     val tokenResponse: AccessTokenResponse = k.tokenManager().grantToken();
        val authModel: AuthModel = AuthModel("");
        return authModel
    }
}