package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("planets")
    suspend fun getPlanets(): Response<PlanetsResponse>
}