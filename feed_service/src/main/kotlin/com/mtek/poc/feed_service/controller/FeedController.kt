package com.mtek.poc.feed_service.controller

import com.mtek.poc.feed_service.model.FeedModel
import com.mtek.poc.feed_service.repository.FeedsRepository
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import javax.annotation.security.RolesAllowed

//import com.mtek.poc.users_service.configs.KeycloakClientConfig;
@RestController
@RequestMapping(value = ["/"])
class FeedController(private val feedRepository: FeedsRepository) {
    @RolesAllowed("goarena-admins")
    @GetMapping("")
    fun all(): Flux<FeedModel> {
        return feedRepository.findAll()
    }

    @RolesAllowed("goarena-users")
    @PostMapping("")
    fun create(@RequestBody feedModel: FeedModel): Mono<FeedModel> {
        //new KeycloakClientConfig().keycloak().tokenManager().getAccessToken()
        return feedRepository.save(feedModel)
    }

    @PostMapping("/auth")
    fun auth(@RequestBody feedModel: FeedModel): Mono<FeedModel> {
        return feedRepository.save(feedModel)
    }

    /*  @GetMapping("/{who:[a-zA-Z]+}")
    public String test(@PathVariable("who") String who) {
        return who;
    }*/
    @GetMapping("/{id}")
    operator fun get(@PathVariable("id") id: Long): Mono<FeedModel> {
        return feedRepository.findById(id)
    }

    @RolesAllowed("goarena-users")
    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: Long, @RequestBody feedModel: FeedModel): Mono<FeedModel> {
        return feedRepository.findById(id)
            .map { p: FeedModel ->
                p.id = feedModel.id
                p
            }
            .flatMap { entity: FeedModel -> feedRepository.save(entity) }
    }

    @RolesAllowed("goarena-admins")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: Long): Mono<Void> {
        return feedRepository.deleteById(id)
    }
}