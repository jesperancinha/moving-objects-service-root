package org.jesperancinha.moving.objects.rest

import java.math.BigDecimal

data class CoordinateSource(
    val x: BigDecimal,
    val y: BigDecimal
);

data class WebCamImageSource(
    val iconUrl: String,
    val thumbnailUrl: String,
    val previewUrl: String,
    val toenailUrlString: String
)

data class WebCamSource(
    val title: String,
    val coordinate: CoordinateSource,
    val wikiInfo: String,
    val active: Boolean,
    val webCamImage: WebCamImageSource
)

data class MovingObjectSource(
    val code: String,
    val name: String,
    val city: String,
    val themeList: List<String>,
    val coordinates: CoordinateSource,
    val pointsOfSale: List<String>,
    val webCamSources: List<WebCamSource>
)

data class MovingObjects(val movingObjects: MutableList<MovingObjectSource>)

data class Page(
    val pageSize: Int,
    val totalElements: Int,
    val pageNumber: Int,
    var totalPages: Int,
    val movingObjects: MovingObjects
)
