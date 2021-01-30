package com.mtek.poc.user_service.controller

//import com.mtek.poc.user_service.configs.KeycloakClientConfig
import com.mtek.poc.user_service.model.UserModel
import com.mtek.poc.user_service.repository.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import javax.annotation.security.RolesAllowed
import javax.persistence.EntityManagerFactory
import javax.validation.Valid
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


//import com.mtek.poc.user_service.configs.KeycloakClientConfig;
@RestController
@RequestMapping(value = ["/"])
class UserController()  {


    @Autowired
    private lateinit var userRepository : UserRepository


   // @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): MutableIterable<UserModel> {
        return userRepository.findAll()
    }

    //  @RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody userModel: UserModel): UserModel {

        return userRepository.save(userModel)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): UserModel? {
        return userRepository.findById(id).orElseThrow { ResourceNotFoundException() }

    }

    // @RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody userModel: UserModel): UserModel? {
        val entity = userRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.first_name = userModel.first_name
        entity.first_name = userModel.last_name
        entity.username = userModel.username
        entity.avatar = userModel.avatar
        return userRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return userRepository.deleteById(id)
    }
}