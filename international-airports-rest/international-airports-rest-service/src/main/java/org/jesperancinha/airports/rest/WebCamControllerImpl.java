package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.dto.WebCamDto;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

@RestController
@RequestMapping("webcams")
public class WebCamControllerImpl {

    private final WebCamService webCamService;

    public WebCamControllerImpl(WebCamService webCamService) {
        this.webCamService = webCamService;
    }

    @org.springframework.web.bind.annotation.GetMapping("/page/{pageSize}/{pageOffset}")
    public Flux<WebCamDto> getCamsByPageSizeAndPageOffset(
            @org.springframework.web.bind.annotation.PathVariable
            int pageSize, @org.springframework.web.bind.annotation.PathVariable
            int pageOffset) {
        return webCamService.getCamsByPageSizeAndPageOffset(pageSize, pageOffset);
    }

    @org.springframework.web.bind.annotation.GetMapping("/location/{longitude}/{latitude}/{kilometers}")
    public Flux<WebCamDto> getCamsByLocationAndRadius(
            @org.springframework.web.bind.annotation.PathVariable
            BigDecimal longitude, @org.springframework.web.bind.annotation.PathVariable
            BigDecimal latitude, @org.springframework.web.bind.annotation.PathVariable
            long kilometers) {
        return webCamService.getCamsByLocationAndRadius(longitude, latitude, kilometers);
    }
}
