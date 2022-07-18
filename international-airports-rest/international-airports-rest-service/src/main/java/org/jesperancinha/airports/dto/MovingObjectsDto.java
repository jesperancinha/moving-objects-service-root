package org.jesperancinha.airports.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.util.List;

public record MovingObjectsDto(String code, String name, CoordinatesDto coordinates, List<WebCamDto> webCams) {
    @JsonbCreator
    @Builder
    public MovingObjectsDto {
    }
}
