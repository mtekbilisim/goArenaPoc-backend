package com.mtek.poc.employee_service.configs;

//
//@Configuration
//class KeycloakClientConfig(
//    @Value("\${keycloak.credentials.secret}")
//    private val secretKey: String,
//    @Value("\${keycloak.resource}")
//    private val clientId: String,
//    @Value("\${keycloak.auth-server-url}")
//    private val authUrl: String,
//    @Value("\${keycloak.realm}")
//    private val realm: String
//) {
//    @Bean
//    fun keycloak(): Keycloak {
//        return KeycloakBuilder.builder()
//            .grantType(CLIENT_CREDENTIALS)
//            .serverUrl(authUrl)
//            .realm(realm)
//            .clientId(clientId)
//            .clientSecret(secretKey)
//            .build()
//    }
//}
