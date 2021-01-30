package com.mtek.poc.user_service.repository
import com.mtek.poc.user_service.model.UserModel
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<UserModel, Long>{
}