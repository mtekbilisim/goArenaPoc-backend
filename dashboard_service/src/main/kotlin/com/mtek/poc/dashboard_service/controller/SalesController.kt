package com.mtek.poc.dashboard_service.controller

import com.mtek.poc.dashboard_service.model.SalesModel
import com.mtek.poc.dashboard_service.repository.SalesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/"])
class SalesController {
    @Autowired
    private lateinit var salesRepository : SalesRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): MutableList<SalesModel> {
        return salesRepository.findAll()
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody salesModel: SalesModel): SalesModel {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return salesRepository.save(salesModel)
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): SalesModel? {
        return salesRepository.findById(id).orElseThrow { ResourceNotFoundException() }
    }

    //@RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody salesModel: SalesModel): SalesModel {
        val entity= salesRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return salesRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return salesRepository.deleteById(id)
    }
}