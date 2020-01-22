package org.jesperancinha.airports.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WebCam {
    private String title;
    private Coordinate coordinate;
    private String wikiInfo;
    private boolean active;
    private WebCamImage webCamImage;
}
