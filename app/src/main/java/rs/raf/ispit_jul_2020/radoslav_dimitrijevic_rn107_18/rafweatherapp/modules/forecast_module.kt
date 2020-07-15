package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.modules

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.local.Database
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.local.ForecastDao
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.remote.WeatherService
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories.ForecastRepository
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories.ForecastRepositoryImpl
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.viewmodel.ForecastViewModel

val forecastModule = module {

    viewModel { ForecastViewModel(repository = get()) }

    single<WeatherService> { create(retrofit = get()) }

    single<ForecastDao> { get<Database>().getForecastDao() }

    single<ForecastRepository> {
        ForecastRepositoryImpl(
            remoteDataSource = get(),
            localDataSource = get()
        )
    }
}