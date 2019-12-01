package ins1der.cleanarch.domain.usecases

import ins1der.cleanarch.domain.models.Employee
import ins1der.cleanarch.domain.repositories.EmployeeRepository
import ins1der.cleanarch.common.Result
import ins1der.cleanarch.common.ResultType

class GetEmployeeUseCase(private val employeeRepository: EmployeeRepository) {

    suspend fun execute(): Result<List<Employee>> {
        val result = employeeRepository.getEmployees()
        return if (result.resultType == ResultType.SUCCESS) {
            // do some operation on result list if needed, for example sorting, etc
            Result.success(result.data)
        } else {
            Result.error(result.error)
        }
    }
}