package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.state

import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.ForecastDetails

sealed class ForecastState {
    object Loading : ForecastState()
    data class ForecastFetched(val city: String, val days: Int) : ForecastState()
    data class ForecastByIdSuccess(val data: ForecastDetails) : ForecastState()
    data class Success(val data: List<Forecast>) : ForecastState()
    data class Error(val message: String) : ForecastState()
}