package org.jesperancinha.airports.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
public class WebCamDto {
    private String title;
    private CoordinatesDto coordinates;
    private String wikiInfo;
    private boolean active;
    private WebCamImageDto webCamImage;
}
