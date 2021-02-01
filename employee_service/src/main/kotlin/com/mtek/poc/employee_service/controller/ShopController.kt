package com.mtek.poc.employee_service.controller

import com.mtek.poc.employee_service.model.ShopModel
import com.mtek.poc.employee_service.model.ShopWithEmployeesModel
import com.mtek.poc.employee_service.repository.ShopGetRepository
import com.mtek.poc.employee_service.repository.ShopPostRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/"])
class ShopController() {


    @Autowired
    private lateinit var shopGetRepository: ShopGetRepository

    @Autowired
    private lateinit var shopPostRepository: ShopPostRepository


    // @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): List<ShopWithEmployeesModel> {
        return shopGetRepository.findAll()
    }

    //  @RolesAllowed("goarena-shops")
    @PostMapping("")
    fun create(@RequestBody shopModel: ShopModel): ShopModel {

        return shopPostRepository.save(shopModel)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ShopWithEmployeesModel? {
        return shopGetRepository.findById(id).orElseThrow { ResourceNotFoundException() }

    }

    // @RolesAllowed("goarena-shops")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody shopModel: ShopModel): ShopModel? {
        val entity: ShopModel = shopPostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.name = shopModel.name
        return shopPostRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return shopGetRepository.deleteById(id)
    }
}