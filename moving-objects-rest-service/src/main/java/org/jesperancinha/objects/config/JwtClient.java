package org.jesperancinha.objects.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by jofisaes on 30/07/2022
 */
@Service
public class JwtClient {
    private String token;

    private final WebClient webClient;

    public JwtClient(
            @Value("${org.jesperancinha.objects.token}")
            String objectsTokenEndpoint) {
        this.webClient = WebClient.builder()
                .build();
        webClient.post()
                .uri(objectsTokenEndpoint)
                .headers(headers -> headers.setBasicAuth("user", "password"))
                .retrieve().bodyToMono(String.class).subscribe(token -> this.token = token);
    }

    public Get get() {
        return new Get();
    }

    public class Get {

        public WebClient.RequestHeadersSpec<?> uri(String uri, Object... uriVariables) {
            return webClient.get().uri(uri, uriVariables)
                    .header("Authorization", String.format("Bearer %s", token)).header("Authorization", String.format("Bearer %s", token));
        }

    }
}
