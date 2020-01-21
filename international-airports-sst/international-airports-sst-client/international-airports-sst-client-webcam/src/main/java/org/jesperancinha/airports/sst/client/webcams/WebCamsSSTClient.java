package org.jesperancinha.airports.sst.client.webcams;

import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface WebCamsSSTClient {
    Mono<WebCamResponse> findWebCamsByPageSizeAndOffset(int pageSize, int pageOffSet);

    Mono<WebCamResponse> findWebCampsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, Long kilometers);
}
