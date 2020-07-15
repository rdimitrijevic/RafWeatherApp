package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui

data class Forecast(
    val id: Long,
    val city: String,
    val averageTemp: Double,
    val date: String,
    val imageURL: String
)