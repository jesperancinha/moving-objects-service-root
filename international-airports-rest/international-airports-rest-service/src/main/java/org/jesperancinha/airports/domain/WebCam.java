package org.jesperancinha.airports.domain;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebCam {
    private String title;
    private Coordinate coordinate;
    private String wikiInfo;
    private boolean active;
    private WebCamImage webCamImage;
}
