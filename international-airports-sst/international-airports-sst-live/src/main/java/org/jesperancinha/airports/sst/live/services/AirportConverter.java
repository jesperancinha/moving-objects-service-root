package org.jesperancinha.airports.sst.live.services;

import org.jesperancinha.airports.sst.client.airports.model.Airport;
import org.jesperancinha.airports.sst.client.airports.model.Location;
import org.jesperancinha.airports.sst.data.AirportDto;
import org.jesperancinha.airports.sst.data.CoordinateDto;

import java.util.Collections;

public class AirportConverter {
    public static AirportDto toAirportDto(Airport airport) {
        return AirportDto.builder()
                .name(airport.getName())
                .city(airport.getCity())
                .code(airport.getCode())
                .coordinates(toCoordinateDto(airport.getLocation()))
                .pointsOfSale(airport.getPointsOfSale())
                .themeList(airport.getThemes())
                .webCams(Collections.emptyList())
                .build();
    }

    private static CoordinateDto toCoordinateDto(Location location) {
        return CoordinateDto.builder()
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .build();
    }
}
