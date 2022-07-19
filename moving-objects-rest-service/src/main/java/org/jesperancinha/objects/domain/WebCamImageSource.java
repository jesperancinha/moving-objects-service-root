package org.jesperancinha.objects.domain;


import jakarta.json.bind.annotation.JsonbCreator;

public record WebCamImageSource(String iconUrl, String thumbnailUrl, String previewUrl, String toenailUrl) {
    @JsonbCreator
    public WebCamImageSource {
    }
}
