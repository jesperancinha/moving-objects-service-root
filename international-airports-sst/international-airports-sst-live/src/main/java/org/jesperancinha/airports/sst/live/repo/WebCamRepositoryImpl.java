package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.webcams.WebCamsSSTClient;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Repository
public class WebCamRepositoryImpl implements WebCamRepository {

    private final WebCamsSSTClient webCamsSSTClient;

    public WebCamRepositoryImpl(WebCamsSSTClient webCamsSSTClient) {
        this.webCamsSSTClient = webCamsSSTClient;
    }

    public Mono<WebCamResponse> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffset) {
        return webCamsSSTClient.findWebCamsByPageSizeAndOffset(pageSize, pageOffset);
    }

    public Mono<WebCamResponse> findCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, long kilometers) {
        return webCamsSSTClient.findWebCampsByLocationAndRadius(latitude, longitude, kilometers);
    }
}
