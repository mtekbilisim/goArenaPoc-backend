package com.mtek.poc.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
public class GoArenaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoArenaGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator GenericServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .filters(f -> f.rewritePath("/users/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://user-service"))
                .route("feed-service", r -> r.path("/feeds/**")
                        .filters(f -> f.rewritePath("/feeds/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://feed-service"))
                .route("file-service", r -> r.path("/files/**")
                        .filters(f -> f.rewritePath("/files/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://file-service"))
                .route("dashboard-service", r -> r.path("/dashboard/**")
                        .filters(f -> f.rewritePath("/dashboard/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://dashboard-service")).build();
    }

}

