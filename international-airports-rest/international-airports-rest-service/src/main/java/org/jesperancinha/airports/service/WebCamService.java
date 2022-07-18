package org.jesperancinha.airports.service;

import org.jesperancinha.airports.dto.WebCamDto;
import org.jesperancinha.airports.repository.WebCamRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@Repository
public class WebCamService {

    private final WebCamRepository webCamRepository;


    public WebCamService(WebCamRepository webCamRepository) {
        this.webCamRepository = webCamRepository;
    }

    public Flux<WebCamDto> getCamsByPageSizeAndPageOffset(int pageSize, int pageOffset) {
        return webCamRepository.findCamsByPageSizeAndPageOffset(pageSize, pageOffset)
                .map(TravelConverter::toWebCamDto);
    }

    public Flux<WebCamDto> getCamsByLocationAndRadius(BigDecimal latitude, BigDecimal longitude, Long radius) {
        return webCamRepository.findCamsByLocationAndRadius(latitude, longitude, radius)
                .map(TravelConverter::toWebCamDto);
    }
}
