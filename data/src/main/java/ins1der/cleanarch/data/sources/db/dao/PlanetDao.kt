package ins1der.cleanarch.data.sources.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ins1der.cleanarch.data.models.db.PlanetEntity
import ins1der.cleanarch.domain.models.Planet

@Dao
interface PlanetDao {

    @Query("SELECT * FROM planets")
    fun getPlanetsLive(): LiveData<List<PlanetEntity>>

    @Query("SELECT * FROM planets")
    fun getPlanets(): List<PlanetEntity>

    @Query("DELETE FROM planets")
    fun deleteAllPlanets()

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun savePlanets(planets: List<PlanetEntity>)

    @Query("UPDATE planets SET population = :population WHERE name = :name")
    fun changePopulationInternal(name: String, population: String)

    @Transaction
    fun changePopulation(planet: Planet, population: Long) {
        changePopulationInternal(planet.name, population.toString())
    }
}