package org.jesperancinha.airport.rest;

import org.jesperancinha.airport.data.FareDto;
import org.jesperancinha.airport.service.FareService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("fares")
public class FareControllerImpl implements FareController {

    private final FareService fareService;

    public FareControllerImpl(FareService fareService) {
        this.fareService = fareService;
    }

    @Override
    public Mono<FareDto> getFare(String originCode, String destinationCode) {
        return fareService.getFare(originCode, destinationCode);
    }
}
