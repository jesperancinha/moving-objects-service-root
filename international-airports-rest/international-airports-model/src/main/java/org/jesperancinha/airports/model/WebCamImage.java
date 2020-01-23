package org.jesperancinha.airports.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebCamImage {
    private String iconUrl;
    private String thumbnailUrl;
    private String previewUrl;
    private String toenailUrl;

}
