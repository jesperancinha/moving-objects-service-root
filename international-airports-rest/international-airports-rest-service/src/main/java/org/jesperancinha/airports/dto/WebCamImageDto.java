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
public class WebCamImageDto {
    private String iconUrl;
    private String thumbnailUrl;
    private String previewUrl;
    private String toenailUrl;
}
