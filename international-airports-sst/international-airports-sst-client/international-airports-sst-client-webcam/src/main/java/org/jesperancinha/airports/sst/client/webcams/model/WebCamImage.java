package org.jesperancinha.airports.sst.client.webcams.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebCamImage {
    private WebCamUrls current;
    private WebCamSizes sizes;
    private WebCamUrls dailight;
    private BigInteger update;
}
