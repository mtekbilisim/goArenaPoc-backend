package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.enums.FeedStatus
import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.model.FeedPlainModel
import com.mtek.poc.feed_service.model.LikeModel
import com.mtek.poc.feed_service.repository.FeedsPostRepository
import com.mtek.poc.feed_service.repository.FeedsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.annotation.security.RolesAllowed

//import com.mtek.poc.users_service.configs.KeycloakClientConfig;
@RestController
@RequestMapping(value = ["/"])
class FeedController() {
    @Autowired
    private lateinit var feedRepository: FeedsRepository

    @Autowired
    private lateinit var feedPostRepository: FeedsPostRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(@RequestParam(name = "keyword", required = false) keyword: String?): ResponseWrap<List<FeedModel>> {
        var result: ResponseWrap<List<FeedModel>>? = null
        if (keyword.isNullOrEmpty())
            result = ResponseWrap<List<FeedModel>>(feedRepository.findAll(Sort.by("id").ascending()).filter { it.status == FeedStatus.APPROVED })
        else
            result = ResponseWrap<List<FeedModel>>(
                feedRepository.findByTitleContainsOrderByIdAsc(keyword).filter { it.status == FeedStatus.APPROVED })
        return result
    }

    //@RolesAllowed("goarena-admins")
    @GetMapping("/admin")
    fun adminAll(@RequestParam(name = "keyword", required = false) keyword: String?): ResponseWrap<List<FeedModel>> {
        var result: ResponseWrap<List<FeedModel>>? = null
        if (keyword.isNullOrEmpty())
            result = ResponseWrap<List<FeedModel>>(feedRepository.findAll(Sort.by("id").ascending()))
        else
            result = ResponseWrap<List<FeedModel>>(feedRepository.findByTitleContainsOrderByIdAsc(keyword))
        return result
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody feedPlainModel: FeedPlainModel): ResponseWrap<FeedPlainModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return ResponseWrap<FeedPlainModel>(feedPostRepository.save(feedPlainModel))
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<FeedModel>? {
        return ResponseWrap<FeedModel>(feedRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
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

    @PutMapping("/status")
    fun update(
        @RequestBody feedPlainModel: List<FeedPlainModel>
    ): ResponseWrap<List<FeedPlainModel>> {
        //val entity = feedPostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return ResponseWrap(feedPostRepository.saveAll(feedPlainModel))
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return feedRepository.deleteById(id)
    }
}