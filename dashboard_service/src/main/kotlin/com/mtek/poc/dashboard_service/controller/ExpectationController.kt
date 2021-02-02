package com.mtek.poc.dashboard_service.controller

import com.mtek.poc.dashboard_service.configs.ResponseWrap
import com.mtek.poc.dashboard_service.model.ExpectationModel
import com.mtek.poc.dashboard_service.repository.ExpectationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/expectations"])
class ExpectationController {
    @Autowired
    private lateinit var expectationRepository : ExpectationRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): ResponseWrap<List<ExpectationModel>> {
        return ResponseWrap(expectationRepository.findAll())
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody expectationModel: ExpectationModel): ResponseWrap<ExpectationModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap(expectationRepository.save(expectationModel))
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<ExpectationModel>? {
        return ResponseWrap(expectationRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody expectationModel: ExpectationModel): ResponseWrap<ExpectationModel> {
        val entity= expectationRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ResponseWrap(expectationRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return expectationRepository.deleteById(id)
    }
}