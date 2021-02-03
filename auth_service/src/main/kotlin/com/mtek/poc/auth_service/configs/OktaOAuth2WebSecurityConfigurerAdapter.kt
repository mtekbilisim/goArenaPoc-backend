package com.mtek.poc.auth_service.configs
/*
import com.okta.spring.boot.oauth.Okta
import org.springframework.context.annotation.Configuration

import org.springframework.security.config.annotation.web.builders.HttpSecurity

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import java.lang.Exception



@Configuration
class OktaOAuth2WebSecurityConfigurerAdapter : WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/token").permitAll()
            .antMatchers("/signin").permitAll()
            .antMatchers("/api/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .anyRequest().authenticated()
            .and()
            .oauth2ResourceServer().jwt() //or .opaqueToken();

        // process CORS annotations
        http.cors().disable()
        http.csrf().disable()

        // force a non-empty response body for 401's to make the response more browser friendly
        Okta.configureResourceServer401ResponseBody(http)
    }
}
*/