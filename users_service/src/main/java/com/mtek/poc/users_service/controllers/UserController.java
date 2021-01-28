package com.mtek.poc.users_service.controllers;

import com.mtek.poc.users_service.model.UserModel;
import com.mtek.poc.users_service.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(value = "/")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository users) {
        this.userRepository = users;
    }

    @GetMapping("")
    public Flux<UserModel> all() {
        return this.userRepository.findAll();
    }

    @PostMapping("")
    public Mono<UserModel> create(@RequestBody UserModel userModel) {
        return this.userRepository.save(userModel);
    }

    @GetMapping("/{id}")
    public Mono<UserModel> get(@PathVariable("id") Integer id) {
        return this.userRepository.findById(id);
    }

    @PutMapping("/{id}")
    public Mono<UserModel> update(@PathVariable("id") Integer id, @RequestBody UserModel userModel) {
        return this.userRepository.findById(id)
                .map(p -> {
                    p.setName(userModel.getName());
                    p.setAvatar(userModel.getAvatar());

                    return p;
                })
                .flatMap(p -> this.userRepository.save(p));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable("id") Integer id) {
        return this.userRepository.deleteById(id);
    }

}