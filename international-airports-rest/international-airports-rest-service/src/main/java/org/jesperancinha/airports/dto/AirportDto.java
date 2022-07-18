package org.jesperancinha.airports.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.util.List;

public record AirportDto(String code, String name, CoordinatesDto coordinates, List<WebCamDto> webCams) {
    @JsonbCreator
    @Builder
    public AirportDto {
    }
}
