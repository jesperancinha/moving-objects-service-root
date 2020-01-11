package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.WebCamDto;
import org.jesperancinha.airports.service.WebCamService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("webcams")
public class WebCamControllerImpl implements WebCamController {

    private final WebCamService webCamService;

    public WebCamControllerImpl(WebCamService webCamService) {
        this.webCamService = webCamService;
    }

    public Mono<WebCamDto> getFare(String originCode, String destinationCode) {
        return webCamService.getFare(originCode, destinationCode);
    }
}
