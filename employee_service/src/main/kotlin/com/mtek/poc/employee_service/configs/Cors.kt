package com.mtek.poc.employee_service.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import org.springframework.web.cors.reactive.CorsWebFilter
import java.util.*


@Configuration
class Cors {
        @Bean
        fun corsWebFilter(): CorsWebFilter {
            val corsConfig = CorsConfiguration()
            corsConfig.allowedOrigins = Collections.singletonList("*")
            corsConfig.maxAge = 3600L
            corsConfig.allowedMethods = Arrays.asList("GET", "POST","PUT","PATCH","DELETE","OPTIONS")
            corsConfig.addAllowedHeader("*")
            val source = UrlBasedCorsConfigurationSource()
            source.registerCorsConfiguration("/**", corsConfig)
            return CorsWebFilter(source)
    }
}