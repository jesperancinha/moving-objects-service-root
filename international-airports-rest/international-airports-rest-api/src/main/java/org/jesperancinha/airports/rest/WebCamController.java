package org.jesperancinha.airports.rest;

import org.jesperancinha.airports.data.WebCamDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;

public interface WebCamController {
    @GetMapping("/page/{pageSize}/{pageOffset}")
    Flux<WebCamDto> getCamsByPageSizeAndPageOffset(@PathVariable int pageSize, @PathVariable int pageOffset);

    @GetMapping("/location/{longitude}/{latitude}/{kilometers}")
    Flux<WebCamDto> getCamsByLocationAndRadius(@PathVariable BigDecimal longitude, @PathVariable BigDecimal latitude, @PathVariable long kilometers);

}
