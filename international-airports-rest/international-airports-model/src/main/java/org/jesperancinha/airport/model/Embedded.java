package org.jesperancinha.airport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Embedded {
    List<Location> locations;
}
