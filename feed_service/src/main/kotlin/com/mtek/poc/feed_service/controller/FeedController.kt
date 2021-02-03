package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.enums.FeedStatus
import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.model.FeedPlainModel
import com.mtek.poc.feed_service.repository.FeedsPostRepository
import com.mtek.poc.feed_service.repository.FeedsRepository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

//import com.mtek.poc.users_service.configs.KeycloakClientConfig;
@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = ["/"])
class FeedController() {
    @Autowired
    private lateinit var feedRepository: FeedsRepository

    @Autowired
    private lateinit var feedPostRepository: FeedsPostRepository

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("")
    fun all(@RequestParam(name = "keyword", required = false) keyword: String?): ResponseWrap<List<FeedModel>> {
        var result: ResponseWrap<List<FeedModel>>? = null
        if (keyword.isNullOrEmpty())
            result = ResponseWrap<List<FeedModel>>(
                feedRepository.findAll(Sort.by("id").descending()).filter { it.status == FeedStatus.APPROVED })
        else
            result = ResponseWrap<List<FeedModel>>(
                feedRepository.findByTitleContainsOrderByIdDesc(keyword).filter { it.status == FeedStatus.APPROVED })
        return result
    }

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/tagged/{tag}")
    fun allByTagName(@PathVariable(name = "tag", required = true) tag: String): ResponseWrap<List<FeedModel>> {
        return ResponseWrap(
            feedRepository.findByTagsContainsOrderByIdDesc(tag).filter { it.status == FeedStatus.APPROVED })
    }

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/user/{id}")
    fun allByUsername(@PathVariable(name = "id", required = false) userId: Long?): ResponseWrap<List<FeedModel>> {
        return ResponseWrap(
            feedRepository.findByUserInOrderByIdAsc(userId).filter { it.status == FeedStatus.APPROVED })
    }

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/admin")
    fun adminAll(@RequestParam(name = "keyword", required = false) keyword: String?): ResponseWrap<List<FeedModel>> {
        var result: ResponseWrap<List<FeedModel>>? = null
        if (keyword.isNullOrEmpty())
            result = ResponseWrap<List<FeedModel>>(feedRepository.findAll(Sort.by("id").descending()))
        else
            result = ResponseWrap<List<FeedModel>>(feedRepository.findByTitleContainsOrderByIdDesc(keyword))
        return result
    }

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/admin/tagged/{tag}")
    fun allByTagNameAdmin(@PathVariable(name = "tag", required = true) tag: String): ResponseWrap<List<FeedModel>> {
        return ResponseWrap(
            feedRepository.findByTagsContainsOrderByIdDesc(tag)
        )
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("")
    fun create(@RequestBody feedPlainModel: FeedPlainModel): ResponseWrap<FeedPlainModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap<FeedPlainModel>(feedPostRepository.save(feedPlainModel))
    }

    @SecurityRequirement(name = "bearer-key")
    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<FeedModel>? {
        return ResponseWrap<FeedModel>(feedRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody feedPlainModel: FeedPlainModel
    ): ResponseWrap<FeedPlainModel> {
        val entity = feedPostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.postDate = LocalDateTime.now()
        entity.title = feedPlainModel.title
        entity.status = feedPlainModel.status
        entity.postType = feedPlainModel.postType
        return ResponseWrap<FeedPlainModel>(feedPostRepository.save(entity))
    }

    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/status")
    fun update(
        @RequestBody feedPlainModel: List<FeedPlainModel>
    ): ResponseWrap<List<FeedPlainModel>> {
        //val entity = feedPostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ResponseWrap(feedPostRepository.saveAll(feedPlainModel))
    }

    //  @RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return feedRepository.deleteById(id)
    }
}