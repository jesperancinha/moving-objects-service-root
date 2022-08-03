package org.jesperancinha.objects.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;
import java.util.Objects;

/**
 * Created by jofisaes on 30/07/2022
 */
@Service
public class JwtClient {
    private final RestTemplate restClient;
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
        this.restClient = new  RestTemplate();
        refreshToken(objectsTokenEndpoint, username, password);
    }

    private void refreshToken(String url, String username, String password) {
        final var authStr = String.format("%s:%s",username, password);
        final var base64Creds = Base64.getEncoder().encodeToString(authStr.getBytes());
        final var headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Creds);
        final var response = new RestTemplate().exchange(url, HttpMethod.POST, new HttpEntity<>(headers), String.class);
        this.token= response.getBody();
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
