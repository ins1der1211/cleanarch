package ins1der.cleanarch.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ins1der.cleanarch.data.models.api.mapToDb
import ins1der.cleanarch.data.models.db.mapToDomain
import ins1der.cleanarch.data.sources.db.PlanetDatabase
import ins1der.cleanarch.data.sources.network.PlanetsApiService
import ins1der.cleanarch.data.sources.prefs.SharedPrefsDataSource
import ins1der.cleanarch.data.utils.error
import ins1der.cleanarch.data.utils.successBody
import ins1der.cleanarch.domain.models.Planet
import ins1der.cleanarch.domain.repositories.PlanetRepository

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