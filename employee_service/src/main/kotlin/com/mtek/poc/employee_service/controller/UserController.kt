package com.mtek.poc.employee_service.controller

import com.mtek.poc.employee_service.model.EmployeeWithShopModel
import com.mtek.poc.employee_service.repository.EmployeeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/users"])
class UserController()  {


    @Autowired
    private lateinit var employeeRepository : EmployeeRepository


   // @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): MutableIterable<EmployeeWithShopModel> {
        return employeeRepository.findAll()
    }

    //  @RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody userModel: EmployeeWithShopModel): EmployeeWithShopModel {

        return employeeRepository.save(userModel)
    }

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: Long): EmployeeWithShopModel? {
        return employeeRepository.findById(id).orElseThrow { ResourceNotFoundException() }

    }

    // @RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @Valid @RequestBody userModel: EmployeeWithShopModel): EmployeeWithShopModel? {
        val entity = employeeRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.first_name = userModel.first_name
        entity.last_name = userModel.last_name
        entity.username = userModel.username
        entity.avatar = userModel.avatar
        return employeeRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return employeeRepository.deleteById(id)
    }
}