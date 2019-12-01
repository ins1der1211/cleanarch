package ins1der.cleanarch.data.repositories

import ins1der.cleanarch.common.Result
import ins1der.cleanarch.data.models.mapToDomain
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.data.utils.error
import ins1der.cleanarch.data.utils.successBody
import ins1der.cleanarch.domain.models.Employee
import ins1der.cleanarch.domain.repositories.EmployeeRepository

class EmployeesRepositoryImpl(private val apiDataSource: ApiDataSource): EmployeeRepository {

    override suspend fun getEmployees(): Result<List<Employee>> {
        val result = apiDataSource.getEmployees()
        return if (result.isSuccessful) {
            Result.success(result.successBody().employees.map { it.mapToDomain() })
        } else {
            Result.error(result.error())
        }
    }

}