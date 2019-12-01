package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.EmployeesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/employees")
    suspend fun getEmployees(): Response<EmployeesResponse>
}