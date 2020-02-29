package ins1der.gifmaker.presentation.di

import ins1der.gifmaker.data.repositories.PeopleRepositoryImpl
import ins1der.gifmaker.data.repositories.PlanetsRepositoryImpl
import ins1der.gifmaker.data.sources.db.PlanetDatabase
import ins1der.gifmaker.data.sources.network.PeopleApiService
import ins1der.gifmaker.data.sources.network.PlanetsApiService
import ins1der.gifmaker.data.sources.prefs.SharedPrefsDataSource
import ins1der.gifmaker.domain.repositories.PeopleRepository
import ins1der.gifmaker.domain.repositories.PlanetRepository
import ins1der.gifmaker.presentation.ui.models.PersonUI
import ins1der.gifmaker.presentation.ui.models.mapToUI
import org.koin.dsl.module

val repositoryModule = module {

    single { createPlanetRepository(get(), get(), get()) }

    single { createPeopleRepository(get()) }
}

fun createPlanetRepository(planetsApiService: PlanetsApiService,
                           planetDatabase: PlanetDatabase,
                           sharedPrefsDataSource: SharedPrefsDataSource): PlanetRepository {
    return PlanetsRepositoryImpl(planetsApiService, planetDatabase, sharedPrefsDataSource)
}

fun createPeopleRepository(peopleApiService: PeopleApiService): PeopleRepository<PersonUI> {
    return PeopleRepositoryImpl(peopleApiService) { it.mapToUI() }
}