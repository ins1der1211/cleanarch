package ins1der.gifmaker.domain.repositories

import androidx.lifecycle.LiveData
import ins1der.gifmaker.domain.models.Planet

interface PlanetRepository {

    val planetsLive: LiveData<List<Planet>>

    suspend fun loadPlanets(force: Boolean): Result<Any>

    suspend fun changePopulation(planet: Planet, population: Long)
}