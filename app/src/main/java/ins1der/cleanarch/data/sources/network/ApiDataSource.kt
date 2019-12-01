package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.api.PeopleResponse
import ins1der.cleanarch.data.models.api.PlanetsResponse
import retrofit2.Response

class ApiDataSource(private val planetsApiService: PlanetsApiService,
                    private val peopleApiService: PeopleApiService) {

    suspend fun getPlanets(): Response<PlanetsResponse> = planetsApiService.getPlanets()

    suspend fun getPeople(): Response<PeopleResponse> = peopleApiService.getPeople()
}