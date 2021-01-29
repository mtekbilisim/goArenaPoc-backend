package com.mtek.poc.user_service.controllers

import com.mtek.poc.user_service.services.RoleService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/role")
class RoleController(
        private val roleService: RoleService
) {
    @GetMapping
    fun findAll() =
            roleService.findAll()
    @PostMapping
    fun createRole(@RequestParam name: String) =
            roleService.create(name)
}