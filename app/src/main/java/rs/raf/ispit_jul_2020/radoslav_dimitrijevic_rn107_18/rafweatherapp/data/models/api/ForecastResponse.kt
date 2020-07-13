package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.api

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForecastResponse(
    @Json(name = "location")
    val location: LocationResponse,

    @Json(name = "forecast")
    val forecasts: ForecastDayResponse
)

@JsonClass(generateAdapter = true)
data class LocationResponse(
    @Json(name = "name")
    val name: String,

    @Json(name = "lat")
    val latitude: Double,

    @Json(name = "lon")
    val longitude: Double
)

@JsonClass(generateAdapter = true)
data class ForecastDayResponse(
    @Json(name = "forecastday")
    val days: List<DaysResponse>
)

@JsonClass(generateAdapter = true)
data class DaysResponse(
    @Json(name = "date")
    val date: String,

    @Json(name = "day")
    val stats: TemperatureResponse
)

@JsonClass(generateAdapter = true)
data class TemperatureResponse(
    @Json(name = "maxtemp_c")
    val maxTemp: Double,

    @Json(name = "mintemp_c")
    val minTemp: Double,

    @Json(name = "maxwind_kph")
    val windSpeed: Double,

    @Json(name = "uv")
    val indexUV: Double,

    @Json(name = "condition")
    val condition: ConditionResponse
)

@JsonClass(generateAdapter = true)
data class ConditionResponse(
    @Json(name = "text")
    val text: String,

    @Json(name = "icon")
    val iconURL: String
)