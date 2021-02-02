package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.model.FeedPlainModel
import com.mtek.poc.feed_service.model.LikeModel
import com.mtek.poc.feed_service.repository.FeedsPostRepository
import com.mtek.poc.feed_service.repository.FeedsRepository
import org.springframework.beans.factory.annotation.Autowired
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
    fun all(): ResponseWrap<List<FeedModel>> {
        return ResponseWrap<List<FeedModel>>(feedRepository.findAll())
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
        entity.postType = feedPlainModel.postType
        return ResponseWrap<FeedPlainModel>(feedPostRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return feedRepository.deleteById(id)
    }
}