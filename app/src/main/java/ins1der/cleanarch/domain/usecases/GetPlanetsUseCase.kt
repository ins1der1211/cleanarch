package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PlanetRepository
import ins1der.cleanarch.common.Result
import ins1der.cleanarch.common.ResultType
import ins1der.cleanarch.domain.models.Planet

class GetPlanetsUseCase(private val planetRepository: PlanetRepository) {

    suspend fun execute(): Result<List<Planet>> {
        val result = planetRepository.getPlanets()
        return if (result.resultType == ResultType.SUCCESS) {
            // do some operation on result list if needed, for example sorting, etc
            Result.success(result.data)
        } else {
            Result.error(result.error)
        }
    }
}