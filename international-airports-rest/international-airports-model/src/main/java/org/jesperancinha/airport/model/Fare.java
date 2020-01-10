package org.jesperancinha.airport.model;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class Fare {
    private BigDecimal amount;
    private String currency;
    private String origin;
    private String destination;
}
