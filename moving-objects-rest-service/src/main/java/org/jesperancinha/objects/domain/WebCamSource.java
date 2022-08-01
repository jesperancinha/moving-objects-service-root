package org.jesperancinha.objects.domain;


import jakarta.json.bind.annotation.JsonbCreator;

public record WebCamSource(String title, CoordinateSource coordinates, String wikiInfo, boolean active, WebCamImageSource webCamImage) {
    @JsonbCreator
    public WebCamSource {
    }
}
