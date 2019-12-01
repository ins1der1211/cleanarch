package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.data.models.api.mapToDb
import ins1der.cleanarch.data.models.api.mapToDomain
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

//    override suspend fun getPlanets(): Result<List<Planet>> {
//        val cached = planetDatabase.planetDao().getPlanets()
//        return if (cached.isEmpty()) {
//            val result = planetsApiService.getPlanets()
//            if (result.isSuccessful) {
//                planetDatabase.planetDao().savePlanets(result.successBody().planets.map { it.mapToDb() })
//                Result.success(result.successBody().planets.map { it.mapToDomain() })
//            } else {
//                Result.error(result.error())
//            }
//        } else {
//            Result.success(cached.map { it.mapToDomain() })
//        }
//    }

    override suspend fun getPlanets(): Result<List<Planet>> {
        val cached = sharedPrefsDataSource.planets
        return if (cached.isNullOrEmpty()) {
            val result = planetsApiService.getPlanets()
            if (result.isSuccessful) {
                sharedPrefsDataSource.planets = result.successBody().planets
                Result.success(result.successBody().planets.map { it.mapToDomain() })
            } else {
                Result.error(result.error())
            }
        } else {
            Result.success(cached.map { it.mapToDomain() })
        }
    }

}