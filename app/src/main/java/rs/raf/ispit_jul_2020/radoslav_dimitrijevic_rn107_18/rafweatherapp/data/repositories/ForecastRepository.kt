package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories

import io.reactivex.Observable
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.Resource
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast

interface ForecastRepository {

    fun fetch(city: String, days: Int): Observable<Resource<Unit>>
    fun getForecastsForCity(city: String, days: Int): Observable<Resource<List<Forecast>>>

}