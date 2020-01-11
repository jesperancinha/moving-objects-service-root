package org.jesperancinha.airports.service;

import org.jesperancinha.airports.data.WebCamDto;
import org.jesperancinha.airports.repository.AirportsRepository;
import org.jesperancinha.airports.repository.WebCamRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.parallel;

@Repository
public class WebCamServiceImpl implements WebCamService {

    private final WebCamRepository webCamRepository;

    private final AirportsRepository airportsRepository;

    public WebCamServiceImpl(WebCamRepository webCamRepository, AirportsRepository airportsRepository) {
        this.webCamRepository = webCamRepository;
        this.airportsRepository = airportsRepository;
    }

    @Override
    public Mono<WebCamDto> getFare(String originCode, String destinationCode) {
        return Mono.from(webCamRepository.findFare(originCode, destinationCode).map(fare ->
                Mono.zip(airportsRepository.findAirportById(originCode).subscribeOn(parallel()),
                        airportsRepository.findAirportById(destinationCode).subscribeOn(parallel()),
                        (origin, destination) -> {
                            return TravelConverter.toFareDto(fare);
                        }).subscribeOn(parallel())).flatMap(Flux::from));
    }
}
