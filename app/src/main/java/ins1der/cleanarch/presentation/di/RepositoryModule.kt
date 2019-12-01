package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.data.repositories.PeopleRepositoryImpl
import ins1der.cleanarch.data.repositories.PlanetsRepositoryImpl
import ins1der.cleanarch.data.sources.db.DbDataSource
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.domain.repositories.PeopleRepository
import ins1der.cleanarch.domain.repositories.PlanetRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { createPlanetRepository(get(), get()) }

    single { createPeopleRepository(get(), get()) }
}

fun createPlanetRepository(apiDataSource: ApiDataSource,
                           dbDataSource: DbDataSource): PlanetRepository {
    return PlanetsRepositoryImpl(apiDataSource, dbDataSource)
}

fun createPeopleRepository(apiDataSource: ApiDataSource,
                           dbDataSource: DbDataSource): PeopleRepository {
    return PeopleRepositoryImpl(apiDataSource, dbDataSource)
}