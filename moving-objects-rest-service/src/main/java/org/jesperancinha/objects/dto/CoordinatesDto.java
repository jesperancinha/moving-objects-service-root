package org.jesperancinha.objects.dto;


import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.math.BigDecimal;

public record CoordinatesDto(BigDecimal x, BigDecimal y) {
    @JsonbCreator
    @Builder
    public CoordinatesDto {
    }
}
