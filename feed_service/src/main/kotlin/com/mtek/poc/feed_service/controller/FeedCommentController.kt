package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.configs.ResponseWrap
import com.mtek.poc.feed_service.model.CommentModel
import com.mtek.poc.feed_service.model.CommentPlainModel
import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.repository.CommentPostRepository
import com.mtek.poc.feed_service.repository.CommentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime

@RestController
@RequestMapping(value = ["/{feedId}/comments"])
class FeedCommentController {
    @Autowired
    private lateinit var commentRepository: CommentRepository

    @Autowired
    private lateinit var commentPostRepository: CommentPostRepository

    //@RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(@PathVariable("feedId") feedId: Long): ResponseWrap<List<CommentModel>> {
        return ResponseWrap<List<CommentModel>>(commentRepository.findByFeedId(feedId))
    }

    //@RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(
        @RequestBody commentPlainModel: CommentPlainModel,
        @PathVariable("feedId") feedId: Long
    ): ResponseWrap<CommentPlainModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        commentPlainModel.feedId = feedId
        return ResponseWrap<CommentPlainModel>(commentPostRepository.save(commentPlainModel))
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): ResponseWrap<CommentModel>? {
        return ResponseWrap(commentRepository.findById(id).orElseThrow { ResourceNotFoundException() })
    }

    //@RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: Long,
        @RequestBody commentPlainModel: CommentPlainModel
    ): ResponseWrap<CommentPlainModel> {
        val entity = commentPostRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        entity.postDate = LocalDateTime.now()
        entity.comment = commentPlainModel.comment
        return ResponseWrap(commentPostRepository.save(entity))
    }

    //  @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return commentRepository.deleteById(id)
    }

}