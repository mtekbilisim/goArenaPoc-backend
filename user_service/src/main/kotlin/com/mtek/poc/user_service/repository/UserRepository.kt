package com.mtek.poc.user_service.repository

import com.mtek.poc.user_service.model.UserModel
import org.springframework.data.repository.reactive.ReactiveCrudRepository


interface UserRepository : ReactiveCrudRepository<UserModel, Long>