package com.mtek.poc.employee_service.controller

import com.mtek.poc.employee_service.model.ShopWithEmployeesModel
import com.mtek.poc.employee_service.repository.ShopRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/shops"])
class ShopController()  {


    @Autowired
    private lateinit var shopRepository : ShopRepository


    // @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): MutableIterable<ShopWithEmployeesModel> {
        return shopRepository.findAll()
    }

    //  @RolesAllowed("goarena-shops")
    @PostMapping("")
    fun create(@RequestBody shopModel: ShopWithEmployeesModel): ShopWithEmployeesModel {

        return shopRepository.save(shopModel)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): ShopWithEmployeesModel? {
        return shopRepository.findById(id).orElseThrow { ResourceNotFoundException() }

    }

    // @RolesAllowed("goarena-shops")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody shopModel: ShopWithEmployeesModel): ShopWithEmployeesModel? {
        val entity :ShopWithEmployeesModel = shopRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.name = shopModel.name
        return shopRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return shopRepository.deleteById(id)
    }
}