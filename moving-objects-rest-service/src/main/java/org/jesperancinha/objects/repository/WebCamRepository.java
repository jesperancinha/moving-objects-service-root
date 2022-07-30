package org.jesperancinha.objects.repository;

import org.jesperancinha.objects.config.JwtClient;
import org.jesperancinha.objects.domain.WebCamSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Repository
public class WebCamRepository {

    @Value("${org.jesperancinha.objects.webcams}")
    private String endpointWebCams;

    public WebCamRepository(JwtClient jwtClient) {
        this.jwtClient = jwtClient;
    }

    private final JwtClient jwtClient;

    public Flux<WebCamSource> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffSet) {
        return jwtClient.get()
                .uri(endpointWebCams + "/page/{originCode}/{destinationCode}", pageSize, pageOffSet)

                .retrieve()
                .bodyToFlux(WebCamSource.class);
    }

    public Flux<WebCamSource> findCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, Long radius) {
        return jwtClient.get()
                .uri(endpointWebCams + "/location/{latitude}/{longitude}/{radius}", latitude, longitude, radius)
                .retrieve()
                .bodyToFlux(WebCamSource.class);
    }
}
