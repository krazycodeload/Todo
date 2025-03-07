package com.k2infosoft.Todo.swaggerconfig

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {
    @Bean
    fun customOpenAPI(): OpenAPI? {
        return OpenAPI()
            .info(
                Info()
                    .title("Todo API")
                    .version("1.0.0")
                    .description("API documentation for my Todo Spring Boot application")
            )
    }
}