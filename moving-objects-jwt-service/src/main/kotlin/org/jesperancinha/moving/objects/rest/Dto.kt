package org.jesperancinha.moving.objects.rest

import java.math.BigDecimal

class CoordinateSource(x: BigDecimal, y: BigDecimal);

class WebCamImageSource(iconUrl: String, thumbnailUrl: String, previewUrl: String, toenailUrlString: String)

class WebCamSource(
    title: String,
    coordinate: CoordinateSource,
    wikiInfo: String,
    active: Boolean,
    webCamImage: WebCamImageSource
)

class MovingObjectSource(
    code: String,
    name: String,
    city: String,
    themeList: List<String>,
    coordinates: CoordinateSource,
    pointsOfSale: List<String>,
    webCamSources: List<WebCamSource>
)

class MovingObjects(movingObjects: List<MovingObjectSource>)

class Page (pageSize: Int, totalElements: Int,pageNumber : Int, totalPages: Int, movingObjects: MovingObjects)
