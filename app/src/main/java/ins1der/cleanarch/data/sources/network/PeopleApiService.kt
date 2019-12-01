package ins1der.cleanarch.data.sources.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface PeopleApiService {

    @GET(".")
    suspend fun getPeople(): Response<ResponseBody>
}