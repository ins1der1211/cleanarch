package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.PlanetsResponse
import retrofit2.Response

class ApiDataSource(private val apiService: ApiService) {

    suspend fun getPlanets(): Response<PlanetsResponse> = apiService.getPlanets()
}