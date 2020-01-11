package org.jesperancinha.airports.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelResponse {

    private Airports airports;

    private Page page;
}
