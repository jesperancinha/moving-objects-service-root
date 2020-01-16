package org.jesperancinha.airports.sst.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@Builder
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WebCamImageDto {
    private String iconUrl;
    private String thumbnailUrl;
    private String previewUrl;
    private String toenailUrl;

}
