package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.R
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.contract.ForecastContract
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.state.ForecastState
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.adapter.ForecastAdapter
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.view.recycler.diff.ForecastDiffItemCallback
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.presentation.viewmodel.ForecastViewModel
import timber.log.Timber

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val mainViewModel: ForecastContract.ViewModel by viewModel<ForecastViewModel>()
    private lateinit var adapter: ForecastAdapter

    private val detailsAction: (Long) -> Unit = {
        Timber.e("Clicked id: %s", it.toString())

        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra(DetailsActivity.FORECAST_ID, it)

        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        init()
        initObservers()
    }

    private fun init() {
        forecast_rv.layoutManager = LinearLayoutManager(this)

        adapter = ForecastAdapter(
            ForecastDiffItemCallback(),
            detailsAction
        )

        forecast_rv.adapter = adapter

    }

    private fun initObservers() {

        search_btn.setOnClickListener {
            if (city_tf.text.isNullOrEmpty()
                || days_tf.text.isNullOrEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Please fill in both fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (days_tf.text.toString().toInt() !in 1..10) {

                Toast.makeText(
                    this,
                    "Enter a minimum of 1 day and maximum of 10 days",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            mainViewModel.fetchByCityForDays(
                city = city_tf.text.toString(),
                days = days_tf.text.toString().toInt()
            )
        }


        mainViewModel.state.observe(this, Observer {
            renderState(it)
        })
    }

    private fun renderState(state: ForecastState) {
        when (state) {
            is ForecastState.ForecastFetched -> {
                Toast.makeText(this, "Data updated", Toast.LENGTH_SHORT).show()
                mainViewModel.getByCityForDays(
                    city = state.city,
                    days = state.days
                )
            }
            is ForecastState.Success -> {
                Timber.e(state.data.toString())
                adapter.submitList(state.data)
                showLoadingState(false)
            }
            is ForecastState.Error -> {
                Timber.e("Forecasts error")
                showLoadingState(false)
                Toast.makeText(this, state.message, Toast.LENGTH_SHORT).show()

                mainViewModel.getByCityForDays(
                    city = city_tf.text.toString(),
                    days = days_tf.text.toString().toInt()
                )
            }
            is ForecastState.Loading -> {
                Timber.e("Forecasts loading")
                showLoadingState(true)
            }
        }
    }


    private fun showLoadingState(loading: Boolean) {
        forecast_rv.visibility = if (!loading) View.VISIBLE else View.INVISIBLE
        loadingPb.visibility = if (loading) View.VISIBLE else View.INVISIBLE
    }

}