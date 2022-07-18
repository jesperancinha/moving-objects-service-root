package org.jesperancinha.airports.domain;

import jakarta.json.bind.annotation.JsonbCreator;

import java.util.List;

public record Airports(List<Airport> airports) {
    @JsonbCreator
    public Airports {
    }
}
