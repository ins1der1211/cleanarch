package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.domain.usecases.ChangeRandomPlanetUseCase
import ins1der.cleanarch.domain.usecases.GetPeopleUseCase
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import ins1der.cleanarch.presentation.ui.models.PersonUI
import org.koin.dsl.module

val useCaseModule = module {

    single { GetPlanetsUseCase(get()) }

    single { GetPeopleUseCase<PersonUI>(get()) }

    single { ChangeRandomPlanetUseCase(get()) }
}