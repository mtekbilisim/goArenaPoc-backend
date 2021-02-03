package com.mtek.poc.gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tags;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
@OpenAPIDefinition(
        info = @Info(
                title = "Gateway API",
                description = "" +
                        "Turkcell GoArena Gateway API",
                contact = @Contact(
                        name = "Emrah TOY",
                        url = "https://www.mtekbilisim.com",
                        email = "emrah.toy@mtekbilisim.com"
                ),

                license = @License(
                        name = "MIT Licence",
                        url = "https://www.wikiwand.com/en/MIT_License")),
        servers = {@Server(url = "http://turkcell.mtek.me:8080"),@Server(url = "http://localhost:8080")}

)
public class GoArenaGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoArenaGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator GenericServiceRouteLocation(RouteLocatorBuilder routeLocatorBuilder) {
        // open-api documentation
        RouteLocator routes = routeLocatorBuilder.routes()
                .route("employees-service", r -> r.path("/employees/**")
                        .filters(f -> f.rewritePath("/employees/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://employees-service"))
                .route("feeds-service", r -> r.path("/feeds/**")
                        .filters(f -> f.rewritePath("/feeds/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://feeds-service"))
                .route("files-service", r -> r.path("/files/**")
                        .filters(f -> f.rewritePath("/files/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://files-service"))
                .route("dashboard-service", r -> r.path("/dashboard/**")
                        .filters(f -> f.rewritePath("/dashboard/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://dashboard-service"))
                .route("auth-service", r -> r.path("/authentication/**")
                        .filters(f -> f.rewritePath("/authentication/?(?<segment>.*)", "/${segment}"))
                        .uri("lb://auth-service"))
                // open-api documentation
                .route("open-api", r -> r.path("/api/**")
                        .filters(f -> f.rewritePath("/api/?(?<segment>.*)", "/${segment}/api"))
                        .uri("lb://gateway")).build();


        return routes;
    }

    @Bean
    @Lazy(false)
    public List<GroupedOpenApi> apis(SwaggerUiConfigParameters swaggerUiConfigParameters, RouteDefinitionLocator locator) {
        List<GroupedOpenApi> groups = new ArrayList<>();

        swaggerUiConfigParameters.addGroup("feeds");
        groups.add(GroupedOpenApi.builder().pathsToMatch("/feeds/**").group("feeds").build());

        swaggerUiConfigParameters.addGroup("employees");
        groups.add(GroupedOpenApi.builder().pathsToMatch("/employees/**").group("feeds").build());

        swaggerUiConfigParameters.addGroup("files");
        groups.add(GroupedOpenApi.builder().pathsToMatch("/files/**").group("files").build());

        swaggerUiConfigParameters.addGroup("dashboard");
        groups.add(GroupedOpenApi.builder().pathsToMatch("/dashboard/**").group("dashboard").build());

        swaggerUiConfigParameters.addGroup("authentication");
        groups.add(GroupedOpenApi.builder().pathsToMatch("/authentication/**").group("authentication").build());


        return groups;
    }


}

