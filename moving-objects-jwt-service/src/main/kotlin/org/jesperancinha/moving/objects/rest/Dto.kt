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
    val title: String? = null,
    val coordinates: CoordinateSource,
    val wikiInfo: String,
    val active: Boolean,
    val webCamImage: WebCamImageSource
)

data class MovingObjectSource(
    val code: String,
    val name: String? = null,
    val city: String,
    val color: String? = null,
    val size: Int = -1,
    val themeList: List<String> = emptyList(),
    val coordinates: CoordinateSource? = null,
    val pointsOfSale: List<String> = emptyList(),
    val webCamSources: List<WebCamSource> = emptyList()
)

data class MovingObjects(val movingObjects: MutableList<MovingObjectSource>)

data class Page(
    val pageSize: Int,
    val totalElements: Int,
    val pageNumber: Int,
    var totalPages: Int,
    val movingObjects: MovingObjects
)
