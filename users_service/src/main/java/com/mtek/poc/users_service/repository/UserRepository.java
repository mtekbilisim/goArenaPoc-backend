package com.mtek.poc.users_service.repository;

import com.mtek.poc.users_service.model.UserModel;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserModel, Integer> {

}
