package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.model.TagModel
import com.mtek.poc.feed_service.repository.TagRepository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = ["/tag"])
class TagController {
    @Autowired
    private lateinit var tagRepository: TagRepository

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("")
    fun all(): ResponseWrap<List<TagModel>> {
        return ResponseWrap<List<TagModel>>(tagRepository.findAll(Sort.by("popularity").ascending()))
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/top")
    fun top(): ResponseWrap<List<TagModel>> {
        return ResponseWrap<List<TagModel>>(tagRepository.findAllTop5ByOrderByPopularityAsc())
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("")
    fun create(
        @RequestBody tagModel : TagModel,
    ): ResponseWrap<TagModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap<TagModel>(tagRepository.save(tagModel))
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody tagModel: TagModel
    ): ResponseWrap<TagModel> {
        val entity = tagRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.tag = tagModel.tag
        entity.popularity = tagModel.popularity
        return ResponseWrap<TagModel>(tagRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return tagRepository.deleteById(id)
    }
}