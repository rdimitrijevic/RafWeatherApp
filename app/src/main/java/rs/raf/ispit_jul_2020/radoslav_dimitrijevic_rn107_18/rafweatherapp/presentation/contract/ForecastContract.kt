package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract

import androidx.lifecycle.LiveData

interface ForecastContract {

    interface ViewModel {

        val state: LiveData<ForecastState>


    }

}