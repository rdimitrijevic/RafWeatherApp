package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "forecasts")
data class ForecastEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    val city: String,
    val longitude: Double,
    val latitude: Double,
    val date: String,

    @ColumnInfo(name = "avg_temp")
    val averageTemp: Double,

    @ColumnInfo(name = "max_temp")
    val maxTemp: Double,

    @ColumnInfo(name = "min_temp")
    val minTemp: Double,

    @ColumnInfo(name = "wind_speed")
    val windSpeed: Double,

    @ColumnInfo(name = "uv_quotient")
    val uvQuotient: Double,

    @ColumnInfo(name = "image_url")
    val imgURL: String
)