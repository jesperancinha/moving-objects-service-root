package org.jesperancinha.airports.sst.client.webcams.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebCamLocation {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String wikipedia;
}
