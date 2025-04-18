package com.timeto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(

                // HTTP
                "http://localhost:8080",
                "http://localhost:5173",
                "http://3.39.38.237",
                "http://3.39.38.237:8080",
                "http://3.39.38.237:5173",

                // HTTPS
                "https://localhost:8080",
                "https://localhost:5173",
                "https://3.39.38.237",
                "https://3.39.38.237:8080",
                "https://3.39.38.237:5173",

                // 도메인
                "http://timeto.digital",
                "https://timeto.digital",
                "http://www.timeto.digital",
                "https://www.timeto.digital",
                "https://time-to.co.kr"
        ));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}