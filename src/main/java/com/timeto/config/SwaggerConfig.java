package com.timeto.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("TimeTO API")
                        .description("TimeTO 시간 관리 서비스 API 문서")
                        .version("v1.0.0"))
                .servers(List.of(
                        new Server().url("/").description("Default Server URL")
                ));
    }
}