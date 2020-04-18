package com.business.manager.gateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route("BMEMPLEADO",
                        r ->
                        r.path("/BMEMPLEADO/**")
                                .filters(f ->
                                        f.rewritePath("/BMEMPLEADO/(?<remaining>.*)",
                                                "/${remaining}"))
                                .uri("lb://BMEMPLEADO")
                        .metadata("management.port", "8000")
                )
                .build();
    }
}
