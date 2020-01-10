package org.jesperancinha.airport.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Location {

    private String code;

    private String name;

    private String description;

    private Coordinate coordinates;

    private Location parent;
}
