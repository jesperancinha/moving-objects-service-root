package org.jesperancinha.moving.objects.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

/**
 * Created by jofisaes on 27/07/2022
 */
@RestController
class TokenController(
    @Autowired
    val encoder: JwtEncoder
) {

    @PostMapping("/token")
    fun token(authentication: Authentication): String {
        val now: Instant = Instant.now()
        val expiry = 36000L
        val scope: String = authentication.authorities
            .map { obj: GrantedAuthority -> obj.authority }
            .joinToString { " " }
        val claims = JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(expiry))
            .subject(authentication.name)
            .claim("scope", scope)
            .build()
        return encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }

    @GetMapping("/objects/jwt/open")
    fun getWelcome() = "Welcome to the Objects Cameras Service Exercise!"
    @GetMapping("/objects/jwt")
    fun getProtectedTest() = "This should be protected!"
}