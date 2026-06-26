package org.airesume.apigatewayservice.config;

//import org.apache.catalina.filters.CorsFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class corsConfig {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration config=new CorsConfiguration();
        //Angular Frontend URL
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        //Allowed HTTP Methods
        config.setAllowedMethods(List.of(
                "GET","POST", "PUT","DELETE","OPTIONS"));
        //set Allowed Header
        config.setAllowedHeaders(List.of("*"));
        //allowed credentials
        config.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return new CorsFilter(source);

    }
}
