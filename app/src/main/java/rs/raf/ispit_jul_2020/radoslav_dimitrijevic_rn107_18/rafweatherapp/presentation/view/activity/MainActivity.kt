package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.R
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract.ForecastContract
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract.ForecastState
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.viewmodel.ForecastViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: ForecastContract.ViewModel by viewModel<ForecastViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        mainViewModel.state.observe(this, Observer {
            renderState(it)
        })

        initObservers()
    }


    private fun initObservers() {
        proba_btn.setOnClickListener {
            mainViewModel.fetchByCityForDays(proba_txt.text.toString(), 5)
        }
    }

    private fun renderState(state: ForecastState) {
        when (state) {
            is ForecastState.ForecastFetched -> {
                //Toast.makeText(this, "Fetched", Toast.LENGTH_SHORT).show()
                mainViewModel.getByCityForDays("Paris", 5)
            }
            is ForecastState.Success -> {
                Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()

                Timber.e(state.data.toString())
            }
            is ForecastState.Error -> {
                Timber.e("Subjects error")
//                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()
            }
            is ForecastState.Loading -> {
                Timber.e("Forecasts loading")
//                showLoadingState(true)
            }
        }
    }

}