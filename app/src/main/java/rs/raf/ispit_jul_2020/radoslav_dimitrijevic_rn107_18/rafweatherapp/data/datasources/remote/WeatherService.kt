package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.remote

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.api.ForecastResponse


interface WeatherService {

    @GET("forecast.json")
    fun getAll(
        @Query("q") city: String,
        @Query("days") days: Int = 10
    ): Observable<ForecastResponse>

}