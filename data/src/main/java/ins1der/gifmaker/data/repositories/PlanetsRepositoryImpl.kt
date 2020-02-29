package ins1der.gifmaker.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ins1der.gifmaker.data.models.api.mapToDb
import ins1der.gifmaker.data.models.db.mapToDomain
import ins1der.gifmaker.data.sources.db.PlanetDatabase
import ins1der.gifmaker.data.sources.network.PlanetsApiService
import ins1der.gifmaker.data.sources.prefs.SharedPrefsDataSource
import ins1der.gifmaker.data.utils.error
import ins1der.gifmaker.data.utils.successBody
import ins1der.gifmaker.domain.models.Planet
import ins1der.gifmaker.domain.repositories.PlanetRepository

class PlanetsRepositoryImpl(private val planetsApiService: PlanetsApiService,
                            private val planetDatabase: PlanetDatabase,
                            private val sharedPrefsDataSource: SharedPrefsDataSource): PlanetRepository {

    override val planetsLive: LiveData<List<Planet>> = Transformations.map(planetDatabase.planetDao().getPlanetsLive()) {
        it.map { it.mapToDomain() }
    }

    override suspend fun loadPlanets(force: Boolean): Result<Any> {
        if (force) {
            planetDatabase.planetDao().deleteAllPlanets()
        }
        val cached = planetDatabase.planetDao().getPlanets()
        if (cached.isEmpty()) {
            val result = planetsApiService.getPlanets()
            if (result.isSuccessful) {
                planetDatabase.planetDao().savePlanets(result.successBody().planets.map { it.mapToDb() })
            } else {
                return Result.failure(result.error())
            }
        }
        return Result.success(Object())
    }

    override suspend fun changePopulation(planet: Planet, population: Long) {
        planetDatabase.planetDao().changePopulation(planet, population)
    }
}