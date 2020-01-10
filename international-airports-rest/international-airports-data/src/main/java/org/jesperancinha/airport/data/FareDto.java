package org.jesperancinha.airport.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
public class FareDto {
    private BigDecimal amount;
    private String currency;
    private LocationDto origin;
    private LocationDto destination;
}
