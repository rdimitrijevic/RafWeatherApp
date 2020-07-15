package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.application

import android.app.Application
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.modules.coreModule
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.modules.forecastModule
import timber.log.Timber

class RafWeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()

        init()
        initKoin()
    }

    private fun init() {
        Timber.plant(Timber.DebugTree())
        Stetho.initializeWithDefaults(this)
    }

    private fun initKoin() {

        val modules = listOf(
            coreModule,
            forecastModule
        )

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@RafWeatherApp)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }

    }

}