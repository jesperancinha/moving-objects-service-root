package org.jesperancinha.objects.dto;

import jakarta.json.bind.annotation.JsonbCreator;
import lombok.Builder;

public record WebCamImageDto(String iconUrl, String thumbnailUrl, String previewUrl, String toenailUrl) {
    @JsonbCreator
    @Builder
    public WebCamImageDto {
    }
}
