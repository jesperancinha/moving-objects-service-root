package org.jesperancinha.airports.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Coordinate {

    private BigDecimal latitude;

    private BigDecimal longitude;

}
