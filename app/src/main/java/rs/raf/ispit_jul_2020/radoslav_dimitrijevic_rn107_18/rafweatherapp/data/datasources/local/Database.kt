package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.room.ForecastEntity

@Database(
    entities = [ForecastEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters()
abstract class Database : RoomDatabase() {

    abstract fun getForecastDao(): ForecastDao

}