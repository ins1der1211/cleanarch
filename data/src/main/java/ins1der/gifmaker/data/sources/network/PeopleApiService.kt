package ins1der.gifmaker.data.sources.network

import ins1der.gifmaker.data.models.api.PeopleResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PeopleApiService {

    @GET(".")
    suspend fun getPeople(@Query("page") page: Int): Response<PeopleResponse>
}