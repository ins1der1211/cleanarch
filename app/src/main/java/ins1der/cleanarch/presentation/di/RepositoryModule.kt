package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.data.repositories.PeopleRepositoryImpl
import ins1der.cleanarch.data.repositories.PlanetsRepositoryImpl
import ins1der.cleanarch.data.sources.db.PeopleDatabase
import ins1der.cleanarch.data.sources.db.PlanetDatabase
import ins1der.cleanarch.data.sources.network.PeopleApiService
import ins1der.cleanarch.data.sources.network.PlanetsApiService
import ins1der.cleanarch.data.sources.prefs.SharedPrefsDataSource
import ins1der.cleanarch.domain.repositories.PeopleRepository
import ins1der.cleanarch.domain.repositories.PlanetRepository
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

fun createPeopleRepository(peopleApiService: PeopleApiService): PeopleRepository {
    return PeopleRepositoryImpl(peopleApiService)
}