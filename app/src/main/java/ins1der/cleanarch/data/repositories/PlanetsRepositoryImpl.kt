package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.data.models.api.mapToDb
import ins1der.cleanarch.data.models.api.mapToDomain
import ins1der.cleanarch.data.models.db.mapToDomain
import ins1der.cleanarch.data.sources.db.DbDataSource
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.data.utils.error
import ins1der.cleanarch.data.utils.successBody
import ins1der.cleanarch.domain.models.Planet
import ins1der.cleanarch.domain.repositories.PlanetRepository

class PlanetsRepositoryImpl(private val apiDataSource: ApiDataSource,
                            private val dbDataSource: DbDataSource): PlanetRepository {

    override suspend fun getPlanets(): Result<List<Planet>> {
        val cached = dbDataSource.getPlanets()
        return if (cached.isEmpty()) {
            val result = apiDataSource.getPlanets()
            if (result.isSuccessful) {
                dbDataSource.savePlanets(result.successBody().planets.map { it.mapToDb() })
                Result.success(result.successBody().planets.map { it.mapToDomain() })
            } else {
                Result.error(result.error())
            }
        } else {
            Result.success(cached.map { it.mapToDomain() })
        }
    }

}