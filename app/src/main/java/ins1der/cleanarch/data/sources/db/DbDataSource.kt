package ins1der.cleanarch.data.sources.db

import ins1der.cleanarch.data.models.db.PlanetEntity

class DbDataSource(private val planetDatabase: PlanetDatabase) {

    suspend fun savePlanets(planets: List<PlanetEntity>) {
        planetDatabase.planetDao().savePlanets(planets)
    }

    suspend fun getPlanets(): List<PlanetEntity> {
        return planetDatabase.planetDao().getPlanets()
    }
}