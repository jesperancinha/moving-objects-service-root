package org.jesperancinha.airports.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

public record WebCamDto(String title, CoordinatesDto coordinates, String wikiInfo, boolean active,
                        WebCamImageDto webCamImage) {
    @JsonbCreator
    @Builder
    public WebCamDto {
    }
}
