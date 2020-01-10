package org.jesperancinha.airport.service;

import org.jesperancinha.airport.data.CoordinateDto;
import org.jesperancinha.airport.data.FareDto;
import org.jesperancinha.airport.data.LocationDto;
import org.jesperancinha.airport.model.Coordinate;
import org.jesperancinha.airport.model.Fare;
import org.jesperancinha.airport.model.Location;

public class TravelConverter {

    public static FareDto toFareDto(final Fare fare) {
        return FareDto.builder()
                .amount(fare.getAmount())
                .currency(fare.getCurrency())
                .build();
    }

    public static LocationDto toLocationDto(final Location location) {
       return LocationDto.builder()
                .code(location.getCode())
                .name(location.getName())
                .description(location.getDescription())
                .coordinates(toCoordinatesDto(location.getCoordinates()))
                .build();
    }

    private static CoordinateDto toCoordinatesDto(final Coordinate coordinate) {
        return CoordinateDto.builder()
                .latitude(coordinate.getLatitude())
                .longitude(coordinate.getLongitude()
                ).build();
    }
}
