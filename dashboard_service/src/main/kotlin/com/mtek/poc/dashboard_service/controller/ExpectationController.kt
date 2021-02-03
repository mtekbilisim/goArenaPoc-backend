package com.mtek.poc.dashboard_service.controller

import com.mtek.poc.dashboard_service.configs.ResponseWrap
import com.mtek.poc.dashboard_service.model.ExpectationModel
import com.mtek.poc.dashboard_service.repository.ExpectationRepository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = ["/expectations"])
class ExpectationController {
    @Autowired
    private lateinit var expectationRepository: ExpectationRepository

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("")
    fun all(
        @RequestParam(name = "employee", required = false) employee: Long?,
        @RequestParam(name = "shop", required = false) shop: Long?
    ): ResponseWrap<List<ExpectationModel>> {
        var result: ResponseWrap<List<ExpectationModel>>? = null

        if (shop != null && employee == null) {
            result = ResponseWrap(expectationRepository.findAllByShopId(shop))
        } else if (shop == null && employee != null) {
            result = ResponseWrap(expectationRepository.findAllByUserId(employee))
        } else {
            result = ResponseWrap(expectationRepository.findAll())
        }
        return result
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("")
    fun create(@RequestBody expectationModel: ExpectationModel): ResponseWrap<ExpectationModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap(expectationRepository.save(expectationModel))
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<ExpectationModel>? {
        return ResponseWrap(expectationRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody expectationModel: ExpectationModel
    ): ResponseWrap<ExpectationModel> {
        val entity = expectationRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ResponseWrap(expectationRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return expectationRepository.deleteById(id)
    }
}