package com.mtek.poc.user_service.controller

//import com.mtek.poc.user_service.configs.KeycloakClientConfig
import com.mtek.poc.user_service.model.AuthModel
import com.mtek.poc.user_service.model.UserModel
import com.mtek.poc.user_service.repository.UserRepository
//import org.keycloak.admin.client.Keycloak
//import org.keycloak.representations.AccessTokenResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.annotation.security.RolesAllowed

//import com.mtek.poc.user_service.configs.KeycloakClientConfig;
@RestController
@RequestMapping(value = ["/"])
class UserController(
    private val userRepository: UserRepository,
   // private val keycloakClientConfig: KeycloakClientConfig
) {
    @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): Flux<UserModel> {
        return userRepository.findAll()
    }

  //  @RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody userModel: UserModel): Mono<UserModel> {

        return userRepository.save(userModel)
    }




    /*  @GetMapping("/{who:[a-zA-Z]+}")
    public String test(@PathVariable("who") String who) {
        return who;
    }*/
    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): Mono<UserModel> {
        return userRepository.findById(id)
    }

   // @RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody userModel: UserModel): Mono<UserModel> {
        return userRepository.findById(id)
            .map { p: UserModel ->
                p.first_name = userModel.first_name
                p.first_name = userModel.last_name
                p.username = userModel.username
                p.avatar = userModel.avatar
                p
            }
            .flatMap { entity: UserModel -> userRepository.save(entity) }
    }

  //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): Mono<Void> {
        return userRepository.deleteById(id)
    }
}