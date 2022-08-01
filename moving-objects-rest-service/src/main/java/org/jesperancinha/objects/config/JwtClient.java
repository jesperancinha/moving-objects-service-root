package org.jesperancinha.objects.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

/**
 * Created by jofisaes on 30/07/2022
 */
@Service
public class JwtClient {
    private String token;

    private final WebClient webClient;
    private final String objectsTokenEndpoint;
    private final String username;
    private final String password;

    public JwtClient(
            @Value("${objects.jwt.token}")
            String objectsTokenEndpoint,
            @Value("${objects.jwt.username}")
            String username,
            @Value("${objects.jwt.password}")
            String password) {
        this.objectsTokenEndpoint = objectsTokenEndpoint;
        this.username = username;
        this.password = password;
        this.webClient = WebClient.builder()
                .build();
        refreshToken(objectsTokenEndpoint, username, password);
    }

    private void refreshToken(String objectsTokenEndpoint, String username, String password) {
        webClient.post()
                .uri(objectsTokenEndpoint)
                .headers(headers -> headers.setBasicAuth(username, password))
                .retrieve().bodyToMono(String.class).subscribe(token -> this.token = token);
    }

    public Get get() {
        return new Get();
    }

    public class Get {

        public WebClient.RequestHeadersSpec<?> uri(String uri, Object... uriVariables) {
            if (Objects.isNull(token)) {
                refreshToken(objectsTokenEndpoint, username, password);
            }
            return webClient.get().uri(uri, uriVariables)
                    .header("Authorization", String.format("Bearer %s", token)).header("Authorization", String.format("Bearer %s", token));
        }

    }
}
