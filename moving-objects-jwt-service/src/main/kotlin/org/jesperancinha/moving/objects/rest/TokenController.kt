package org.jesperancinha.moving.objects.rest

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoder
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

/**
 * Created by jofisaes on 27/07/2022
 */
@RestController
@RequestMapping
class TokenController(
    @Autowired
    val encoder: JwtEncoder
) {
    @PostMapping("/token")
    fun token(authentication: Authentication): String {
        val now: Instant = Instant.now()
        val scope: String = authentication.authorities
            .map { obj: GrantedAuthority -> obj.authority }
            .joinToString { " " }
        val claims = JwtClaimsSet.builder()
            .issuer("mos-jwt")
            .issuedAt(now)
            .expiresAt(now.plusSeconds(36000L))
            .subject(authentication.name)
            .claim("scope", scope)
            .build()
        return encoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }
}