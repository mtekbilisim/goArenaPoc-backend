package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.repository.FeedsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.rest.webmvc.ResourceNotFoundException
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.*
import javax.annotation.security.RolesAllowed

//import com.mtek.poc.users_service.configs.KeycloakClientConfig;
@RestController
@RequestMapping(value = ["/"])
class FeedController() {
    @Autowired
    private lateinit var feedRepository : FeedsRepository

    @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): MutableList<FeedModel> {
        return feedRepository.findAll()
    }

    @RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody feedModel: FeedModel): FeedModel {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return feedRepository.save(feedModel)
    }

    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): FeedModel? {
        return feedRepository.findById(id).orElseThrow { ResourceNotFoundException() }
    }

    @RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody feedModel: FeedModel): FeedModel {
        val entity= feedRepository.findById(id).orElseThrow { ResourceNotFoundException() }
        return feedRepository.save(entity)
    }

    @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long) {
        return feedRepository.deleteById(id)
    }
}