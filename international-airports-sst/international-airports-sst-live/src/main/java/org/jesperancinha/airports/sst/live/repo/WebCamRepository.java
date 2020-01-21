package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface WebCamRepository {
    Mono<WebCamResponse> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffset);

    Mono<WebCamResponse> findCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, long kilometers);
}
