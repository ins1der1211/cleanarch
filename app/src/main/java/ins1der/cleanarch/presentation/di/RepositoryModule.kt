package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.data.repositories.PlanetsRepositoryImpl
import ins1der.cleanarch.data.sources.db.DbDataSource
import ins1der.cleanarch.data.sources.network.ApiDataSource
import ins1der.cleanarch.domain.repositories.PlanetRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { createPlanetRepository(get(), get()) }

}

fun createPlanetRepository(apiDataSource: ApiDataSource,
                           dbDataSource: DbDataSource): PlanetRepository {
    return PlanetsRepositoryImpl(apiDataSource, dbDataSource)
}