package ins1der.cleanarch.presentation.di

import android.content.Context
import androidx.room.Room
import ins1der.cleanarch.data.sources.db.PlanetDatabase
import org.koin.dsl.module

val localStorageModule = module {

    single { createPlanetDatabase(get()) }
}

private fun createPlanetDatabase(ctx: Context): PlanetDatabase {
    return Room.databaseBuilder(ctx, PlanetDatabase::class.java, "planets").build()
}