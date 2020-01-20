package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.webcams.WebCamsSSTClient;
import org.jesperancinha.airports.sst.client.webcams.model.WebCamResponse;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class WebCamRepositoryImpl implements WebCamRepository {

    private final WebCamsSSTClient webCamsSSTClient;

    public WebCamRepositoryImpl(WebCamsSSTClient webCamsSSTClient) {
        this.webCamsSSTClient = webCamsSSTClient;
    }

    @Override
    public Mono<WebCamResponse> findCamsByPageSizeAndPageOffset(int pageSize, int pageOffset) {
        return webCamsSSTClient.findWebCamsByPageSizeAndOffset(pageSize, pageOffset);
    }
}
