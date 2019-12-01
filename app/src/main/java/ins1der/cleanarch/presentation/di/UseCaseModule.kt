package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.domain.usecases.GetEmployeeUseCase
import org.koin.dsl.module

val useCaseModule = module {

    single { GetEmployeeUseCase(get()) }
}