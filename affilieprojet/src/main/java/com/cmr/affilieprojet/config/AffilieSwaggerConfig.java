package com.cmr.affilieprojet.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AffilieSwaggerConfig {

    @Bean
    public OpenAPI apiInfo() {
        return new OpenAPI()
            .info(new Info()
                .title("API Gestion Affiliés - CMR")
                .description("Documentation de l'API REST pour la gestion des affiliés et enfants.")
                .version("1.0.0"));
    }
}
