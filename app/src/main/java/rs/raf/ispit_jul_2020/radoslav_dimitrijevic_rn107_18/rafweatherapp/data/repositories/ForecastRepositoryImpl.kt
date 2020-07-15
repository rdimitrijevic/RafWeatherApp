package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories

import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.local.ForecastDao
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.remote.WeatherService
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.Resource
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.room.ForecastEntity
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.ForecastDetails
import timber.log.Timber

class ForecastRepositoryImpl(
    private val remoteDataSource: WeatherService,
    private val localDataSource: ForecastDao
) : ForecastRepository {

    override fun fetchForecasts(city: String, days: Int): Observable<Resource<Unit>> {
        Timber.e("repo: Fetching...")

        return remoteDataSource.getAll(city, days)
            .doOnNext {
                val cityName = it.location.name
                val lon = it.location.longitude
                val lat = it.location.latitude

                val entities = it.forecasts
                    .days.map {
                        ForecastEntity(
                            id = 0,
                            city = cityName,
                            latitude = lat,
                            longitude = lon,
                            averageTemp = it.stats.averageTemp,
                            minTemp = it.stats.minTemp,
                            maxTemp = it.stats.maxTemp,
                            windSpeed = it.stats.windSpeed,
                            date = it.date,
                            uvQuotient = it.stats.indexUV,
                            imgURL = it.stats.condition.iconURL
                        )
                    }

                localDataSource.deleteAndInsert(cityName, entities)
            }
            .map {
                Resource.Success(Unit)
            }
    }

    override fun getByCityForDays(
        city: String,
        days: Int
    ): Observable<Resource<List<Forecast>>> {
        Timber.e("repo: Getting...")

        return localDataSource.getByCityForDays(city, days)
            .map {
                Resource.Success(it.map {
                    Forecast(
                        id = it.id,
                        city = it.city,
                        averageTemp = it.averageTemp,
                        date = it.date,
                        imageURL = it.imgURL
                    )
                })
            }
    }

    override fun getById(id: Long): Single<Resource<ForecastDetails>> {
        Timber.e("repo: Getting by id")
        return localDataSource.getById(id)
            .map {
                val res = ForecastDetails(
                    id = it.id,
                    city = it.city,
                    lon = it.longitude,
                    lat = it.latitude,
                    date = it.date,
                    maxTemp = it.maxTemp,
                    minTemp = it.minTemp,
                    windSpeed = it.windSpeed,
                    uvQuotient = it.uvQuotient
                )

                Resource.Success(res)
            }
    }
}