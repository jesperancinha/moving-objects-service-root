package org.jesperancinha.objects.repository;

import org.jesperancinha.objects.config.JwtClient;
import org.jesperancinha.objects.domain.WebCamSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public class WebCamRepository {

    @Value("${objects.jwt.webcams}")
    private String webCamsEndpoint;

    public WebCamRepository(JwtClient jwtClient) {
        this.jwtClient = jwtClient;
    }

    private final JwtClient jwtClient;

    public Flux<WebCamSource> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffSet) {
        return jwtClient.get()
                .uri(webCamsEndpoint + "/{pageSize}/{pageOffSet}", pageSize, pageOffSet)
                .retrieve()
                .bodyToFlux(WebCamSource.class);
    }

    public Flux<WebCamSource> findCamsByLocationAndRadius(BigDecimal x, BigDecimal y, Long radius) {
        return jwtClient.get()
                .uri(webCamsEndpoint + "/location/{x}/{y}/{radius}", x, y, radius)
                .retrieve()
                .bodyToFlux(WebCamSource.class);
    }

    public Mono<byte[]> getImageFromCode(String code) {
        return jwtClient.get()
                .uri(String.format("%s/camera/{code}", webCamsEndpoint), code)
                .header("Content-Type", "image/jpeg")
                .retrieve()
                .bodyToMono(byte[].class);
    }
}
