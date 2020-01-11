package org.jesperancinha.airports.sst.data;


import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
public class CoordinateDto {

    private BigDecimal latitude;

    private BigDecimal longitude;

}
