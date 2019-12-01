package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.api.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApiService {

    @GET(".")
    suspend fun getPeople(): Response<PeopleResponse>
}