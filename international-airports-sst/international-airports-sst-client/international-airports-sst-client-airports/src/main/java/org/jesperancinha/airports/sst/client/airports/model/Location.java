package org.jesperancinha.airports.sst.client.airports.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class Location {

    private BigDecimal longitude;

    private BigDecimal latitude;

}
