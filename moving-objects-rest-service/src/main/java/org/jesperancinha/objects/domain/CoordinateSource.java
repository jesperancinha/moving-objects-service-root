package org.jesperancinha.objects.domain;


import jakarta.json.bind.annotation.JsonbCreator;

import java.math.BigDecimal;

public record CoordinateSource(BigDecimal x, BigDecimal y) {
    @JsonbCreator
    public CoordinateSource {
    }
}
