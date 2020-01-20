package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.WebCamDto;
import reactor.core.publisher.Flux;

public interface WebCamService {
    Flux<WebCamDto> getCamsByPageSizeAndPageOffse(int pageSize, int pageOffset);
}
