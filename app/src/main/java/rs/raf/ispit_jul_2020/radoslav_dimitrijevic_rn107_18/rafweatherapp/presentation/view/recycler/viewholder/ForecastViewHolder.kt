package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.forecast_viewholder.view.*
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.R
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.ui.Forecast
import timber.log.Timber

class ForecastViewHolder(
    override val containerView: View,
    detailsAction: (Int) -> Unit
) : RecyclerView.ViewHolder(containerView),
    LayoutContainer {

    init {
        containerView.setOnClickListener {
            if (adapterPosition != RecyclerView.NO_POSITION)
                detailsAction.invoke(adapterPosition)
        }
    }

    fun bind(forecast: Forecast) {
        containerView.viewholder_city.text = forecast.city
        containerView.viewholder_date.text = forecast.date
        containerView.viewholder_temp.text = String.format("%.1fC", forecast.averageTemp)


        Picasso
            .get()
            .load("http:" + forecast.imageURL)
            .into(
                containerView.viewholder_img,
                object : Callback {
                    override fun onSuccess() {
                        Timber.e("Image load successful for id:%s", forecast.id)
                        Timber.e("http:%s", forecast.imageURL)
                    }

                    override fun onError(e: Exception?) {
                        Timber.e("Image load unsuccessful for id:%s", forecast.id)
                        Timber.e("http:%s", forecast.imageURL)
                        Picasso
                            .get()
                            .load(R.drawable.ic_default_img)
                            .into(containerView.viewholder_img)
                    }

                }
            )
    }
}