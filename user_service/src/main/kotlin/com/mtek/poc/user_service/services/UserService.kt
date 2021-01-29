package com.mtek.poc.user_service.services

import com.mtek.poc.user_service.model.UserModel
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.RoleRepresentation
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import javax.ws.rs.core.Response

@Service
class UserService(
        private val keycloak: Keycloak,
        @Value("\${keycloak.realm}")
        private val realm: String
) {
    fun findAll(): List<UserRepresentation> =
            keycloak
                    .realm(realm)
                    .users()
                    .list()

    fun findByUsername(username: String): List<UserRepresentation> =
            keycloak
                    .realm(realm)
                    .users()
                    .search(username)

    fun findById(id: String): UserRepresentation =
            keycloak
                    .realm(realm)
                    .users()
                    .get(id)
                    .toRepresentation()

    fun assignToGroup(userId: String, groupId: String) {
        keycloak
                .realm(realm)
                .users()
                .get(userId)
                .joinGroup(groupId)
    }

    fun assignRole(userId: String, roleRepresentation: RoleRepresentation) {
        keycloak
                .realm(realm)
                .users()
                .get(userId)
                .roles()
                .realmLevel()
                .add(listOf(roleRepresentation))
    }

    fun create(request: UserModel): Response? {
        val password = preparePasswordRepresentation(request.password)
        val user = prepareUserRepresentation(request, password)

        return keycloak
                .realm(realm)
                .users()
                .create(user)
    }

    private fun preparePasswordRepresentation(
            password: String
    ): CredentialRepresentation {
        val cR = CredentialRepresentation()
        cR.isTemporary = false
        cR.type = CredentialRepresentation.PASSWORD
        cR.value = password
        return cR
    }

    private fun prepareUserRepresentation(
            request: UserModel,
            cR: CredentialRepresentation
    ): UserRepresentation {
        val newUser = UserRepresentation()
        newUser.username = request.username
        newUser.credentials = listOf(cR)
        newUser.isEnabled = true
        newUser.singleAttribute("avatar", request.avatar)
        newUser.firstName = request.first_name
        newUser.lastName = request.last_name
        newUser.email = request.email
        newUser.isEmailVerified = true
        return newUser
    }
}