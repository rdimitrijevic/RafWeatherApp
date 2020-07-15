package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui

data class ForecastDetails(
    val id: Long,
    val city: String,
    val lon: Double,
    val lat: Double,
    val date: String,
    val maxTemp: Double,
    val minTemp: Double,
    val windSpeed: Double,
    val uvQuotient: Double
)