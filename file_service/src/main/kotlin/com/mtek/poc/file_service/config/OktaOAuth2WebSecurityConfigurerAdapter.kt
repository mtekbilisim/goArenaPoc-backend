package com.mtek.poc.file_service.config
import com.okta.spring.boot.oauth.Okta
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import java.lang.Exception



@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // process CORS annotations
        http.cors()
        http.csrf().disable()
        http.authorizeRequests()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/downloadFile/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt() //or .opaqueToken();



        // force a non-empty response body for 401's to make the response more browser friendly
        Okta.configureResourceServer401ResponseBody(http)
    }

    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearer-key",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    )
            )
    }
    @Bean
    fun corsConfigurer(): WebMvcConfigurer? {
        return object : WebMvcConfigurer {
            override fun addCorsMappings(registry: CorsRegistry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
            }
        }
    }
}
