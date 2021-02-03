package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.model.LikeModel
import com.mtek.poc.feed_service.model.LikePlainModel
import com.mtek.poc.feed_service.model.MediaModel
import com.mtek.poc.feed_service.repository.LikePostRepository
import com.mtek.poc.feed_service.repository.LikeRepository
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping(value = ["/{feedId}/likes"])
class FeedLikeController {
    @Autowired
    private lateinit var likeRepository: LikeRepository

    @Autowired
    private lateinit var likePostRepository: LikePostRepository

    //@RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @GetMapping("")
    fun all(@PathVariable("feedId") feedId: Long): ResponseWrap<List<LikeModel>> {
        return ResponseWrap<List<LikeModel>>(likeRepository.findByFeedId(feedId))
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PostMapping("")
    fun create(
        @RequestBody likePlainModel: LikePlainModel,
        @PathVariable("feedId") feedId: Long
    ): ResponseWrap<LikePlainModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        likePlainModel.feedId = feedId
        return ResponseWrap<LikePlainModel>(likePostRepository.save(likePlainModel))
    }

    @GetMapping("/{id}")
    @SecurityRequirement(name = "bearer-key")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<LikeModel>? {
        return ResponseWrap<LikeModel>(likeRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @SecurityRequirement(name = "bearer-key")
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody likePlainModel: LikePlainModel
    ): ResponseWrap<LikePlainModel> {
        val entity = likePostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.postDate = LocalDateTime.now()
        return ResponseWrap<LikePlainModel>(likePostRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @SecurityRequirement(name = "bearer-key")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return likeRepository.deleteById(id)
    }
}