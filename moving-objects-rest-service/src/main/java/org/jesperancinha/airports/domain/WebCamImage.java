package org.jesperancinha.airports.domain;


import jakarta.json.bind.annotation.JsonbCreator;

public record WebCamImage(String iconUrl, String thumbnailUrl, String previewUrl, String toenailUrl) {
    @JsonbCreator
    public WebCamImage {
    }
}
