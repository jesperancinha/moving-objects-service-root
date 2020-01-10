package org.jesperancinha.airport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TravelResponse {

    private Embedded _embedded;

    private Page page;
}
