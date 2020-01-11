package org.jesperancinha.airports.sst.client.airports.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Airport {
    private String airportId;

    private String code;

    private String name;

    private Location location;

    private String cityId;

    private String city;

    private String countryCode;

    private List<String> themes;

    private List<String> pointsOfSale;
}
