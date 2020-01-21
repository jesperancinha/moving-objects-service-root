package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.WebCamDto;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public interface WebCamService {
    Flux<WebCamDto> getCamsByPageSizeAndPageOffset(int pageSize, int pageOffset);

    Flux<WebCamDto> getCamsByLocationAndRadius(BigDecimal longitude, BigDecimal latitude, long kilometers);
}
