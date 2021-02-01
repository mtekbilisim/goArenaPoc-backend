package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.model.LikeModel
import com.mtek.poc.feed_service.model.LikePlainModel
import com.mtek.poc.feed_service.repository.LikePostRepository
import com.mtek.poc.feed_service.repository.LikeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/{feedId}/likes"])
class FeedLikeController {
    @Autowired
    private lateinit var likeRepository: LikeRepository

    @Autowired
    private lateinit var likePostRepository: LikePostRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(@PathVariable("feedId") feedId: Long): List<LikeModel> {
        return likeRepository.findByFeedId(feedId)
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(
        @RequestBody likePlainModel: LikePlainModel,
        @PathVariable("feedId") feedId: Long
    ): LikePlainModel {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        likePlainModel.feedId=feedId
        return likePostRepository.save(likePlainModel)
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): LikeModel? {
        return likeRepository.findById(id).orElseThrow { ResourceNotFoundException() }
    }

    //@RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody likePlainModel: LikePlainModel): LikePlainModel {
        val entity = likePostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.postDate = LocalDateTime.now()
        return likePostRepository.save(entity)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return likeRepository.deleteById(id)
    }
}