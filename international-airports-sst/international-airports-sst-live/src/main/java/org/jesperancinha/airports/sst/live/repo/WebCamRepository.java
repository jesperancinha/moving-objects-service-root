package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import reactor.core.publisher.Mono;

public interface WebCamRepository {
    Mono<WebCamResponse> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffset);
}
