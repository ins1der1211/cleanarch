package ins1der.gifmaker.domain.usecases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import ins1der.gifmaker.domain.repositories.PlanetRepository
import ins1der.gifmaker.domain.models.Planet

class GetPlanetsUseCase(private val planetRepository: PlanetRepository) {

    val planetsLive: LiveData<List<Planet>>
        get() {
            val live = MediatorLiveData<List<Planet>>()
            live.addSource(planetRepository.planetsLive) {
                live.postValue(it.shuffled())
            }
            return live
        }

    suspend fun execute(force: Boolean): Result<Any> = planetRepository.loadPlanets(force)
}