package org.jesperancinha.objects.security;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * Created by jofisaes on 29/08/2022
 */
@Configuration
@EnableWebFlux
public class CorsConfiguration implements WebFluxConfigurer {

    @Value("${okta.oauth2.issuer}")
    private String issuer;

    @Override
    public void addCorsMappings(@NotNull CorsRegistry corsRegistry) {
        corsRegistry
                .addMapping("/**")
                .allowedOrigins("*")
                .allowedOrigins(issuer)
                .allowedMethods("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

}
