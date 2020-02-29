package ins1der.gifmaker.presentation.di

import ins1der.gifmaker.domain.usecases.ChangeRandomPlanetUseCase
import ins1der.gifmaker.domain.usecases.GetPeopleUseCase
import ins1der.gifmaker.domain.usecases.GetPlanetsUseCase
import ins1der.gifmaker.presentation.ui.models.PersonUI
import org.koin.dsl.module

val useCaseModule = module {

    single { GetPlanetsUseCase(get()) }

    single { GetPeopleUseCase<PersonUI>(get()) }

    single { ChangeRandomPlanetUseCase(get()) }
}