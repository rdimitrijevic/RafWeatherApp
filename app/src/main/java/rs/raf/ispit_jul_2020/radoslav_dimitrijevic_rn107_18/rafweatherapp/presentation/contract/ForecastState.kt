package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract

import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast

sealed class ForecastState {
    object Loading : ForecastState()
    object ForecastFetched : ForecastState()
    data class ForecastByIdSuccess(val data: Forecast) : ForecastState()
    data class Success(val data: List<Forecast>) : ForecastState()
    data class Error(val message: String) : ForecastState()
}