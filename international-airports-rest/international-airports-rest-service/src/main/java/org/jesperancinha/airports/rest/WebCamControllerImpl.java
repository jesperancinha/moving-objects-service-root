package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.WebCamDto;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@RestController
@RequestMapping("webcams")
public class WebCamControllerImpl implements WebCamController {

    private final WebCamService webCamService;

    public WebCamControllerImpl(WebCamService webCamService) {
        this.webCamService = webCamService;
    }

    public Flux<WebCamDto> getCamsByPageSizeAndPageOffset(int pageSize, int pageOffset) {
        return webCamService.getCamsByPageSizeAndPageOffset(pageSize, pageOffset);
    }

    public Flux<WebCamDto> getCamsByLocationAndRadius(BigDecimal longitude, BigDecimal latitude, long kilometers) {
        return webCamService.getCamsByLocationAndRadius(longitude, latitude, kilometers);
    }
}
