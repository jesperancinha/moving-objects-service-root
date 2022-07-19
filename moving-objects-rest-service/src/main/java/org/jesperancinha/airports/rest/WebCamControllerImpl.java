package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.dto.WebCamDto;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/page/{pageSize}/{pageOffset}")
    public Flux<WebCamDto> getCamsByPageSizeAndPageOffset(
            @PathVariable
            final int pageSize,
            @PathVariable
            final int pageOffset
    ) {
        return webCamService.getCamsByPageSizeAndPageOffset(pageSize, pageOffset);
    }

    @GetMapping("/location/{x}/{y}/{radius}")
    public Flux<WebCamDto> getCamsByLocationAndRadius(
            @PathVariable
            final BigDecimal x,
            @PathVariable
            final BigDecimal y,
            @PathVariable
            final long radius
    ) {
        return webCamService.getCamsByLocationAndRadius(x, y, radius);
    }
}
