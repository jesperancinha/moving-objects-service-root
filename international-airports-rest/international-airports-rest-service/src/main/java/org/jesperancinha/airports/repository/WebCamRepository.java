package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.WebCam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Repository
public class WebCamRepository {

    @Value("${org.jesperancinha.airport.webcams:http://localhost:8080/webcams}")
    private String endpointWebCams;

    private final WebClient webClient;

    public WebCamRepository(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<WebCam> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffSet) {
        return webClient.get()
                .uri(endpointWebCams + "/page/{originCode}/{destinationCode}", pageSize, pageOffSet)
                .retrieve()
                .bodyToFlux(WebCam.class);
    }


    public Flux<WebCam> findCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, Long radius) {
        return webClient.get()
                .uri(endpointWebCams + "/location/{latitude}/{longitude}/{radius}", latitude, longitude, radius)
                .retrieve()
                .bodyToFlux(WebCam.class);
    }
}
