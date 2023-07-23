package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("sgeol-time-series-path", r -> r
                        .path("/sgeol-time-series/**")
                        .filters(f -> f.rewritePath("/sgeol-time-series/property/(?<path>)/exists",  "/sgeol-time-series/property/${path}/exists"))
                        .uri("http://localhost:8052/"))
                .route("sgeol-time-series-path", r -> r
                        .path("/sgeol-time-series/entity/**")
                        .filters(f -> f.rewritePath("/sgeol-time-series/property/(?<path>)/exists",  "/sgeol-time-series/property/${path}/exists"))
                        .uri("http://localhost:8052/")
                ).build();
    }
}
