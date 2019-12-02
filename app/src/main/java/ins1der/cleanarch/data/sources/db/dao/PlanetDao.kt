package ins1der.cleanarch.data.sources.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ins1der.cleanarch.data.models.db.PlanetEntity

@Dao
interface PlanetDao {

    @Query("SELECT * FROM planets")
    fun getPlanets(): List<PlanetEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePlanets(planets: List<PlanetEntity>)
}