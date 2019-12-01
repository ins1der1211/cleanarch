package ins1der.cleanarch.data.sources.network

import ins1der.cleanarch.data.models.EmployeesResponse
import ins1der.cleanarch.data.sources.network.ApiService
import retrofit2.Response

class ApiDataSource(private val apiService: ApiService) {

    suspend fun getEmployees(): Response<EmployeesResponse> = apiService.getEmployees()
}