package ins1der.cleanarch.domain.repositories

import ins1der.cleanarch.domain.models.Planet

interface PlanetRepository {

    suspend fun getPlanets(): Result<List<Planet>>
}