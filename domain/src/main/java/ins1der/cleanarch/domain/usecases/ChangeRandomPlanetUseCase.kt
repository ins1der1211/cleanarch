package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.repositories.PlanetRepository

class ChangeRandomPlanetUseCase(private val planetRepository: PlanetRepository) {

    suspend fun execute() {
        planetRepository.planetsLive.value?.let {
            val planet = it.shuffled().find {
                it.population == UNKNOWN_POPULATION ||
                it.population.toLong() != POPULATION
            }
            planet?.let { planetRepository.changePopulation(it, POPULATION) }
        }
    }
}

private const val POPULATION = 666L
private const val UNKNOWN_POPULATION = "unknown"