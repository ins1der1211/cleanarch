package ins1der.gifmaker.data.sources.network

import ins1der.gifmaker.data.models.api.PlanetsResponse
import retrofit2.Response
import retrofit2.http.GET

interface PlanetsApiService {

    @GET(".")
    suspend fun getPlanets(): Response<PlanetsResponse>
}