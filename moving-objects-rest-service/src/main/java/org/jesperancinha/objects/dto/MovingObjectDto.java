package org.jesperancinha.objects.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

import java.util.List;

public record MovingObjectDto(String code, String name, CoordinatesDto coordinates, List<WebCamDto> webCams) {
    @JsonbCreator
    @Builder
    public MovingObjectDto {
    }
}
