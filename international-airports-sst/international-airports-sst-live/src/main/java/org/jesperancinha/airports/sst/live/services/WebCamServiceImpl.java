package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.data.WebCamDto;
import org.jesperancinha.airports.sst.live.repo.WebCamRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Service
public class WebCamServiceImpl implements WebCamService {

    private final WebCamRepository webCamRepository;

    public WebCamServiceImpl(WebCamRepository webCamRepository) {
        this.webCamRepository = webCamRepository;
    }

    public final Flux<WebCamDto> getCamsByPageSizeAndPageOffset(int pageSize, int pageOffset) {
        return webCamRepository.findCamsByPageSizeAndPageOffset(pageSize, pageOffset)
                .map(webCamResponse -> webCamResponse.getResult().getWebcams())
                .flatMapMany(Flux::fromIterable)
                .map(WebCamConverter::toWebCamDto);

    }

    public final Flux<WebCamDto> getCamsByLocationAndRadius(BigDecimal longitude, BigDecimal latitude, long kilometers) {
        return webCamRepository.findCamsByLocationAndRadius(longitude, latitude, kilometers)
                .map(webCamResponse -> webCamResponse.getResult().getWebcams())
                .flatMapMany(Flux::fromIterable)
                .map(WebCamConverter::toWebCamDto);

    }
}
