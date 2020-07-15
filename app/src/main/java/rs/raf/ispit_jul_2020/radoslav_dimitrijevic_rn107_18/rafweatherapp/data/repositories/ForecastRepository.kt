package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories

import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.Resource
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.ForecastDetails

interface ForecastRepository {

    fun fetchForecasts(city: String, days: Int): Observable<Resource<Unit>>
    fun getByCityForDays(city: String, days: Int): Observable<Resource<List<Forecast>>>
    fun getById(id: Long): Single<Resource<ForecastDetails>>
}