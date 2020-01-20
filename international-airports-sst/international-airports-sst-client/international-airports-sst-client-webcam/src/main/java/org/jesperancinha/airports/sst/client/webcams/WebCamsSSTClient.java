package org.jesperancinha.airports.sst.client.webcams;

import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import reactor.core.publisher.Mono;

public interface WebCamsSSTClient {
    Mono<WebCamResponse> findWebCamsByPageSizeAndOffset(int pageSize, int pageOffSet);
}
