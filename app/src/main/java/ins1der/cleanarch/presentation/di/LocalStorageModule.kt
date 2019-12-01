package ins1der.cleanarch.presentation.di

import android.content.Context
import androidx.room.Room
import ins1der.cleanarch.data.sources.db.DbDataSource
import ins1der.cleanarch.data.sources.db.PlanetDatabase
import org.koin.dsl.module

val localStorageModule = module {

    single { createPlanetDatabase(get()) }

    single { createDbDataSource(get()) }
}

private fun createDbDataSource(planetDatabase: PlanetDatabase): DbDataSource {
    return DbDataSource(planetDatabase)
}

private fun createPlanetDatabase(ctx: Context): PlanetDatabase {
    return Room.databaseBuilder(ctx, PlanetDatabase::class.java, "planets").build()
}