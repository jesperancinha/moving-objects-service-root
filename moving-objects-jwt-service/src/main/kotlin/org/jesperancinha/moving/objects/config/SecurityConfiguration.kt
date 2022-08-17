package org.jesperancinha.moving.objects.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.web.access.server.BearerTokenServerAccessDeniedHandler
import org.springframework.security.oauth2.server.resource.web.server.BearerTokenServerAuthenticationEntryPoint
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey


/**
 * Created by jofisaes on 27/07/2022
 */
@Configuration
class SecurityConfiguration(
    @Value("\${objects.jwt.public.key}")
    var rsaPublicKey: RSAPublicKey,

    @Value("\${objects.jwt.private.key}")
    var rsaPrivateKey: RSAPrivateKey
) {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http.authorizeExchange { authorize ->
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
        }
            .csrf().disable()
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer { obj -> obj.jwt() }
            .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
            .exceptionHandling { exceptions ->
                exceptions.authenticationEntryPoint(BearerTokenServerAuthenticationEntryPoint())
                    .accessDeniedHandler(BearerTokenServerAccessDeniedHandler())

            }.build()

    @Bean
    fun authenticationManager(): ReactiveAuthenticationManager {
        return UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService())
    }

    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder = NimbusReactiveJwtDecoder.withPublicKey(rsaPublicKey).build()

    @Bean
    fun jwtEncoder(): JwtEncoder =
        NimbusJwtEncoder(ImmutableJWKSet(JWKSet(RSAKey.Builder(rsaPublicKey).privateKey(rsaPrivateKey).build())))

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }

    @Bean
    fun userDetailsService(): MapReactiveUserDetailsService? {
        val user = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .roles("USER")
            .build()
        return MapReactiveUserDetailsService(user)
    }
}
