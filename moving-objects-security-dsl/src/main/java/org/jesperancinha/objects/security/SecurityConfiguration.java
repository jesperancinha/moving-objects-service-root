package org.jesperancinha.objects.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Created by jofisaes on 07/08/2022
 */
@Configuration
public class SecurityConfiguration {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        return httpSecurity.authorizeExchange(authorize ->
                authorize
                        .pathMatchers("/webjars/**")
                        .permitAll()
                        .pathMatchers("/info/jwt/open/**")
                        .permitAll()
                        .pathMatchers("/webcams/jwt/open/**")
                        .permitAll()
                        .pathMatchers("/v3/**")
                        .permitAll()
                        .pathMatchers("/actuator/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                        .and()
                        .oauth2ResourceServer().jwt()).build();

    }
}
