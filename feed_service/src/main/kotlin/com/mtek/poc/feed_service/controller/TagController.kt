package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.model.TagModel
import com.mtek.poc.feed_service.repository.TagRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/tag"])
class TagController {
    @Autowired
    private lateinit var tagRepository: TagRepository

    @GetMapping("")
    fun all(): ResponseWrap<List<TagModel>> {
        return ResponseWrap<List<TagModel>>(tagRepository.findAll(Sort.by("popularity").ascending()))
    }

    @GetMapping("/top")
    fun top(): ResponseWrap<List<TagModel>> {
        return ResponseWrap<List<TagModel>>(tagRepository.findAllTop5ByOrderByPopularityAsc())
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(
        @RequestBody tagModel : TagModel,
    ): ResponseWrap<TagModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap<TagModel>(tagRepository.save(tagModel))
    }

    //@RolesAllowed("goarena-users")
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
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return tagRepository.deleteById(id)
    }
}