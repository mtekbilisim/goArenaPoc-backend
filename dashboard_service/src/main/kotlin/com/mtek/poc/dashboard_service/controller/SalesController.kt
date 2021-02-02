package com.mtek.poc.dashboard_service.controller

import com.mtek.poc.dashboard_service.configs.ResponseWrap
import com.mtek.poc.dashboard_service.model.ExpectationModel
import com.mtek.poc.dashboard_service.model.SalesModel
import com.mtek.poc.dashboard_service.repository.SalesRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/"])
class SalesController {
    @Autowired
    private lateinit var salesRepository: SalesRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(
        @RequestParam(name = "employee", required = false) employee: Long?,
        @RequestParam(name = "shop", required = false) shop: Long?
    ): ResponseWrap<List<SalesModel>> {
        var result: ResponseWrap<List<SalesModel>>? = null

        if (shop != null && employee == null) {
            result = ResponseWrap(salesRepository.findAllByShopId(shop))
        } else if (shop == null && employee != null) {
            result = ResponseWrap(salesRepository.findAllByUserId(employee))
        } else {
            result = ResponseWrap(salesRepository.findAll())
        }
        return result
    }


    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody salesModel: SalesModel): ResponseWrap<SalesModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap(salesRepository.save(salesModel))
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<SalesModel>? {
        return ResponseWrap(salesRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody salesModel: SalesModel): ResponseWrap<SalesModel> {
        val entity = salesRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ResponseWrap(salesRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return salesRepository.deleteById(id)
    }
}