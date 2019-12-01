package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.data.models.mapToDomain
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.data.utils.error
import ins1der.cleanarch.data.utils.successBody
import ins1der.cleanarch.domain.models.Planet
import ins1der.cleanarch.domain.repositories.PlanetRepository

class PlanetsRepositoryImpl(private val apiDataSource: ApiDataSource): PlanetRepository {

    override suspend fun getPlanets(): Result<List<Planet>> {
        val result = apiDataSource.getPlanets()
        return if (result.isSuccessful) {
            Result.success(result.successBody().planets.map { it.mapToDomain() })
        } else {
            Result.error(result.error())
        }
    }

}