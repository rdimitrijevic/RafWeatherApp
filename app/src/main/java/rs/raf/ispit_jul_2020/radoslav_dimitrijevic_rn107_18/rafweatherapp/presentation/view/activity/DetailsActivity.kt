package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.R
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract.ForecastContract
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.state.ForecastState
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.viewmodel.ForecastViewModel
import timber.log.Timber

class DetailsActivity : AppCompatActivity(R.layout.activity_details), OnMapReadyCallback {

    private val detailsViewModel: ForecastContract.ViewModel by viewModel<ForecastViewModel>()


    private lateinit var mMap: GoogleMap

    companion object {
        const val FORECAST_ID: String = "FORECAST_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        init()
    }

    private fun init() {
        val id = intent.getLongExtra(DetailsActivity.FORECAST_ID, 0)


        detailsViewModel.state.observe(this, Observer {
            renderState(it)
        })

        detailsViewModel.getById(id)
    }


    private fun renderState(state: ForecastState) {
        when (state) {
            is ForecastState.Loading -> {
                Timber.e("Loading details")
            }
            is ForecastState.ForecastByIdSuccess -> {

                city_date_tf.text = state.data.city + ", " + state.data.date
                max_temp_tf.text =
                    getString(R.string.daily_maximum) + String.format("%.1fC", state.data.maxTemp)
                min_temp_tf.text =
                    getString(R.string.daily_minimum) + String.format("%.1fC", state.data.minTemp)
                wind_speed_tf.text =
                    getString(R.string.wind_speed) + String.format("%.1f", state.data.windSpeed)
                uv_tf.text =
                    getString(R.string.uv_radiation) + String.format("%.1f", state.data.uvQuotient)

                val marker = LatLng(
                    state.data.lat,
                    state.data.lon
                )

                mMap.addMarker(MarkerOptions().position(marker).title(state.data.city))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(marker))
            }
            is ForecastState.Error -> {
                Timber.e("Error")
                Toast.makeText(
                    this,
                    state.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}