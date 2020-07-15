package rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.datasources.local

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import rs.raf.ispit_jul_2020.radoslav_dimitrijevic_rn107_18.rafweatherapp.data.models.room.ForecastEntity

@Dao
abstract class ForecastDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<ForecastEntity>): Completable

    @Query("DELETE FROM forecasts WHERE city = :city")
    abstract fun deleteForCity(city: String): Completable

    @Query("SELECT * FROM forecasts WHERE city = :city LIMIT :days")
    abstract fun getByCityForDays(city: String, days: Int): Observable<List<ForecastEntity>>

    @Query("SELECT * FROM forecasts WHERE id = :id")
    abstract fun getById(id: Long): Single<ForecastEntity>

    @Transaction
    open fun deleteAndInsert(city: String, entities: List<ForecastEntity>) {
        deleteForCity(city).blockingAwait()
        insertAll(entities).blockingAwait()
    }
}