package org.jesperancinha.airports.dto;


import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.math.BigDecimal;

public record CoordinatesDto(BigDecimal latitude, BigDecimal longitude) {
    @JsonbCreator
    @Builder
    public CoordinatesDto {
    }
}
