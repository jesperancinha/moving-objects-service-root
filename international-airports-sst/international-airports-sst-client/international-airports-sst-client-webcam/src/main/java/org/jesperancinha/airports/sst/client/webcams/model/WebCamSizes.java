package org.jesperancinha.airports.sst.client.webcams.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class WebCamSizes {
    private WebCamSize icon;
    private WebCamSize thumbnail;
    private WebCamSize preview;
    private WebCamSize toenail;
}
