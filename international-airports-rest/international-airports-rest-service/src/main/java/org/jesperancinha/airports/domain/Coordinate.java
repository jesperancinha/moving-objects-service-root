package org.jesperancinha.airports.domain;


import jakarta.json.bind.annotation.JsonbCreator;

import java.math.BigDecimal;

public record Coordinate(BigDecimal latitude, BigDecimal longitude) {
    @JsonbCreator
    public Coordinate {
    }
}
