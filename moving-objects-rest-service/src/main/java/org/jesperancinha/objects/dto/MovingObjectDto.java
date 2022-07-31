package org.jesperancinha.objects.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.util.List;

public record MovingObjectDto(String name, String code, String color, Integer size, CoordinatesDto coordinates, List<WebCamDto> webCams) {
    @JsonbCreator
    @Builder
    public MovingObjectDto {
    }
}
