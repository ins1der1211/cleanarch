package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PlanetRepository
import ins1der.cleanarch.domain.models.Planet

class GetPlanetsUseCase(private val planetRepository: PlanetRepository) {

    suspend fun execute(): Result<List<Planet>> {
        val result = planetRepository.getPlanets()
        if (result.isSuccess) {
            // do some operation on result list if needed, for example sorting, etc
            return Result.success(result.getOrDefault(listOf()))
        }
        return result
    }
}