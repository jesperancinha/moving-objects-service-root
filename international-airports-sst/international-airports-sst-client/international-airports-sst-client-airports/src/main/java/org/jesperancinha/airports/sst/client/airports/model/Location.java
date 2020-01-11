package org.jesperancinha.airports.sst.client.airports.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Location {

    private BigDecimal longitude;

    private BigDecimal latitude;

}
