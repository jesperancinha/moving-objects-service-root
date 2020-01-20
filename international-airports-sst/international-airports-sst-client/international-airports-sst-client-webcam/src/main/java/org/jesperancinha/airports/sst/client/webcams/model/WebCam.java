package org.jesperancinha.airports.sst.client.webcams.model;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class WebCam {
    private String id;

    private String status;

    private String title;

    private WebCamImage image;

    private WebCamLocation location;
}
