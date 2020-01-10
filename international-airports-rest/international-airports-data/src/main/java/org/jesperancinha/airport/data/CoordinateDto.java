package org.jesperancinha.airport.data;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CoordinateDto {

    private BigDecimal latitude;

    private BigDecimal longitude;

}
