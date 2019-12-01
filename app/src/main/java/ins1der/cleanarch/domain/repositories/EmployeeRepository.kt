package ins1der.cleanarch.domain.repositories

import ins1der.cleanarch.domain.models.Employee
import ins1der.cleanarch.common.Result

interface EmployeeRepository {

    suspend fun getEmployees(): Result<List<Employee>>
}