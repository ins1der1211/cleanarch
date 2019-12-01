package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.domain.repositories.EmployeeRepository
import ins1der.cleanarch.domain.usecases.GetEmployeeUseCase
import org.koin.dsl.module

val useCaseModule = module {

    factory { createGetEmployeeUseCase(get()) }
}

fun createGetEmployeeUseCase(employeeRepository: EmployeeRepository): GetEmployeeUseCase {
    return GetEmployeeUseCase(employeeRepository)
}