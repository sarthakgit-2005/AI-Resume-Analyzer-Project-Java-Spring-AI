package org.airesume.apigatewayservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
public class routeConfig {

    @Bean
    public RouterFunction<ServerResponse> gatewayRoutes() {

        return route("resume-service")

                .GET("/api/resumes/**", http("http://localhost:8092"))
                .POST("/api/resumes/**", http("http://localhost:8092"))
                .PUT("/api/resumes/**", http("http://localhost:8092"))
                .DELETE("/api/resumes/**", http("http://localhost:8092"))

                .GET("/api/parser/**", http("http://localhost:8093"))
                .POST("/api/parser/**", http("http://localhost:8093"))

                .GET("/api/jobdesc/**", http("http://localhost:8091"))
                .POST("/api/jobdesc/**", http("http://localhost:8091"))
                .PUT("/api/jobdesc/**", http("http://localhost:8091"))
                .DELETE("/api/jobdesc/**", http("http://localhost:8091"))

                .GET("/api/ai/**", http("http://localhost:8094"))
                .POST("/api/ai/**", http("http://localhost:8094"))

                .build();
    }
}