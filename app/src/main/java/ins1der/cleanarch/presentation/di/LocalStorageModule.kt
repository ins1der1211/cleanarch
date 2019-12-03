package ins1der.cleanarch.presentation.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import ins1der.cleanarch.data.sources.db.PlanetDatabase
import ins1der.cleanarch.data.sources.prefs.SharedPrefsDataSource
import org.koin.dsl.module

val localStorageModule = module {

    single { createPlanetDatabase(get()) }

    single { createSharedPrefsDataSource(get(), get()) }
}

private fun createPlanetDatabase(ctx: Context): PlanetDatabase {
    return Room.databaseBuilder(ctx, PlanetDatabase::class.java, "planets_db").build()
}

private fun createSharedPrefsDataSource(ctx: Context, moshi: Moshi): SharedPrefsDataSource {
    return SharedPrefsDataSource(ctx, moshi)
}