package org.jesperancinha.moving.objects.config

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager
import org.springframework.security.config.Customizer
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService
import org.springframework.security.core.userdetails.User.withDefaultPasswordEncoder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
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
    var rsaPrivateKey: RSAPrivateKey,

    @Value("\${objects.jwt.username}")
    var username: String,

    @Value("\${objects.jwt.password}")
    var password: String,

    @Value("\${objects.jwt.roles}")
    var roles: Array<String>,
) {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain =
        http
            .logout { logout ->
                logout
                    .requiresLogout(ServerWebExchangeMatchers.pathMatchers(HttpMethod.GET, "/logoff"))
            }
            .authorizeExchange { authorize ->
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
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .httpBasic(Customizer.withDefaults())
            .oauth2ResourceServer { oauth2 ->
                oauth2.jwt { jwt ->
                    jwt.jwtDecoder(jwtDecoder())
                }
            }
            .build()

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
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    /**
     * This deprecated usage is here because this is a test application.
     * This should not be used in a production environment.
     * What this does is to allow us to loging to an application with a fixed username/password combination, which is never, ever advised to do.
     * So this is here purely for demonstration purposes.
     */
    @Bean
    @SuppressWarnings("deprecated")
    fun userDetailsService(): MapReactiveUserDetailsService? {
        val user = withDefaultPasswordEncoder()
            .username(username)
            .password(password)
            .roles(*roles)
            .build()
        return MapReactiveUserDetailsService(user)
    }
}

@Configuration
class SwaggerConfig {
    @Bean
    fun staticResources(): RouterFunction< ServerResponse> {
        return RouterFunctions.resources("/webjars/**", ClassPathResource("META-INF/resources/webjars/"))
    }
}