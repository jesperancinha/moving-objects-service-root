package org.jesperancinha.airports.sst.live.repo;

import org.jesperancinha.airports.sst.client.airports.AirportsSSTClient;
import org.jesperancinha.airports.sst.client.airports.model.Airport;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class AirportsRepoImpl implements AirportsRepo {

    private final AirportsSSTClient airportsSSTClient;

    public AirportsRepoImpl(AirportsSSTClient airportsSSTClient) {
        this.airportsSSTClient = airportsSSTClient;
    }

    public Flux<Airport> findAirportsByCitySearchWord(final String searchWord){
        return airportsSSTClient.findAiportsBySearchWord(searchWord);
    }
    public Mono<Airport> findAirportByCode(final String code){
        return airportsSSTClient.findAirportByCode(code);
    }
}
