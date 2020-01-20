package org.jesperancinha.airports.sst.live.rest;

import org.jesperancinha.airports.sst.data.WebCamDto;
import org.jesperancinha.airports.sst.live.services.WebCamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/live/webcams")
public class WebCamController {

    private final WebCamService webCamService;

    public WebCamController(WebCamService webCamService) {
        this.webCamService = webCamService;
    }

    @GetMapping(value = "/{pageSize}/{pageOffset}")
    public Flux<WebCamDto> getCamsByPageSizeAndPageOffse(@PathVariable int pageSize, @PathVariable int pageOffset){
        return webCamService.getCamsByPageSizeAndPageOffse(pageSize, pageOffset);


    }
}
