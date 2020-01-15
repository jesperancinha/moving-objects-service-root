package org.jesperancinha.airports.sst.client.webcams.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WebCamResponse {

    private String status;

    private WebCamPage result;
}
