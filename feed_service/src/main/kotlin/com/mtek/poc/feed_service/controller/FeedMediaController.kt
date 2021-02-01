package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.model.MediaModel
import com.mtek.poc.feed_service.repository.MediaRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/{feedId}/medias"])
class FeedMediaController {
    @Autowired
    private lateinit var mediaRepository: MediaRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(@PathVariable("feedId") feedId: Long): List<MediaModel> {
        return mediaRepository.findByFeedId(feedId)
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(
        @RequestBody mediaModel: MediaModel,
        @PathVariable("feedId") feedId: Long
    ): MediaModel {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        mediaModel.feedId=feedId
        return mediaRepository.save(mediaModel)
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return mediaRepository.deleteById(id)
    }
}