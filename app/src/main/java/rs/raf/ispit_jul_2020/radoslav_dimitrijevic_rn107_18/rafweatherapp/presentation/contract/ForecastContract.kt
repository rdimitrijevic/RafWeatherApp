package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract

import androidx.lifecycle.LiveData
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.state.ForecastState

interface ForecastContract {

    interface ViewModel {

        val state: LiveData<ForecastState>

        fun fetchByCityForDays(city: String, days: Int)
        fun getByCityForDays(city: String, days: Int)
        fun getById(cityId: Long)

    }

}