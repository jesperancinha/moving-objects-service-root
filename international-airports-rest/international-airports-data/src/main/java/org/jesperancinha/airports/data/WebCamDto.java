package org.jesperancinha.airports.data;

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
    private CoordinateDto coordinate;
    private String wikiInfo;
    private boolean active;
    private WebCamImageDto webCamImage;
}
