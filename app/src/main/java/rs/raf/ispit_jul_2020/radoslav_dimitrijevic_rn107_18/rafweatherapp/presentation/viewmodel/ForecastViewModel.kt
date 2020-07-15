package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.Resource
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.repositories.ForecastRepository
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract.ForecastContract
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.state.ForecastState
import timber.log.Timber

class ForecastViewModel(
    private val repository: ForecastRepository
) : ForecastContract.ViewModel,
    ViewModel() {
    override val state: MutableLiveData<ForecastState> = MutableLiveData<ForecastState>()

    private val subscriptions: CompositeDisposable = CompositeDisposable()

    override fun fetchByCityForDays(city: String, days: Int) {
        Timber.e("Fetching...")
        val subscription = repository.fetchForecasts(city, days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> state.value = ForecastState.Loading
                        is Resource.Success -> state.value =
                            ForecastState.ForecastFetched(city, days)
                    }
                },
                {
                    state.value = ForecastState.Error(it.message ?: "Error while fetching")
                }
            )

        subscriptions.add(subscription)
    }

    override fun getByCityForDays(city: String, days: Int) {
        Timber.e("Getting...")

        val subscription = repository.getByCityForDays(city, days)
            .startWith(Resource.Loading())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Loading -> state.value = ForecastState.Loading
                        is Resource.Success -> state.value = ForecastState.Success(it.data)
                    }
                },
                {
                    state.value = ForecastState.Error(it.message ?: "Database error")
                }
            )

        subscriptions.add(subscription)
    }

    override fun getById(cityId: Long) {
        Timber.e("Getting by id...")

        val subscription = repository.getById(cityId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    when (it) {
                        is Resource.Success -> state.value =
                            ForecastState.ForecastByIdSuccess(it.data)
                    }
                },
                {
                    state.value = ForecastState.Error(it.message ?: "DatabaseError")
                }
            )

        subscriptions.add(subscription)
    }

    override fun onCleared() {
        super.onCleared()
        subscriptions.dispose()
    }
}