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
    public RouteLocator userServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("user-service", r -> r.path("/users/**")
                        .filters(f -> f.rewritePath("/users/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://user-service")).build();
    }

    @Bean
    public RouteLocator feedServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("feed-service", r -> r.path("/feed/**")
                        .filters(f -> f.rewritePath("/feed/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://feed-service")).build();
    }

    @Bean
    public RouteLocator fileServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("files-service", r -> r.path("/files/**")
                        .filters(f -> f.rewritePath("/files/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://files-service")).build();
    }

    @Bean
    public RouteLocator dashboardServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("dashboard-service", r -> r.path("/dashboard/**")
                        .filters(f -> f.rewritePath("/dashboard/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://dashboard-service")).build();
    }

}

