package org.jesperancinha.airports.domain;


import jakarta.json.bind.annotation.JsonbCreator;

import java.math.BigDecimal;

public record Coordinate(BigDecimal x, BigDecimal y) {
    @JsonbCreator
    public Coordinate {
    }
}
