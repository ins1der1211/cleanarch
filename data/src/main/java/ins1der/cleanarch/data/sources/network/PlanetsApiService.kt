package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.api.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlanetsApiService {

    @GET(".")
    suspend fun getPlanets(): Response<PlanetsResponse>
}