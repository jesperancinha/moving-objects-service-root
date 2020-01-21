package org.jesperancinha.airports.repository;

import org.jesperancinha.airports.model.WebCam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface WebCamRepository {
    Flux<WebCam> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffset);

    Flux<WebCam> findCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, long kilometers);
}
