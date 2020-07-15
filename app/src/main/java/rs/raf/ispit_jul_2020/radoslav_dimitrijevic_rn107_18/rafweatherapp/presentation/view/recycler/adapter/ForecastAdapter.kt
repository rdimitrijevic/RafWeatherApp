package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.R
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.diff.ForecastDiffItemCallback
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.viewholder.ForecastViewHolder

class ForecastAdapter(
    forecastDiffItemCallback: ForecastDiffItemCallback,
    private val detailsAction: (Long) -> Unit
) : ListAdapter<Forecast, ForecastViewHolder>(forecastDiffItemCallback) {


    private val detailsLambda: (Int) -> Unit = {
        val id = getItem(it).id
        detailsAction.invoke(id)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val containerView = layoutInflater.inflate(R.layout.forecast_viewholder, parent, false)
        return ForecastViewHolder(
            containerView,
            detailsLambda
        )
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}