package org.jesperancinha.airport.service;

import org.jesperancinha.airport.data.FareDto;
import org.jesperancinha.airport.repository.FareRepository;
import org.jesperancinha.airport.repository.LocationRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.core.scheduler.Schedulers.parallel;

@Repository
public class FareServiceImpl implements FareService {

    private final FareRepository fareRepository;

    private final LocationRepository locationRepository;

    public FareServiceImpl(FareRepository fareRepository, LocationRepository locationRepository) {
        this.fareRepository = fareRepository;
        this.locationRepository = locationRepository;
    }

    @Override
    public Mono<FareDto> getFare(String originCode, String destinationCode) {
        return Mono.from(fareRepository.findFare(originCode, destinationCode).map(fare ->
                Mono.zip(locationRepository.findLocationById(originCode).subscribeOn(parallel()),
                        locationRepository.findLocationById(destinationCode).subscribeOn(parallel()),
                        (origin, destination) -> {
                            final FareDto fareDto = TravelConverter.toFareDto(fare);
                            fareDto.setOrigin(TravelConverter.toLocationDto(origin));
                            fareDto.setDestination(TravelConverter.toLocationDto(destination));
                            return fareDto;
                        }).subscribeOn(parallel())).flatMap(Flux::from));
    }
}
