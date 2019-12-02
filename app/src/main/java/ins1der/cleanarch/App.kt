package ins1der.cleanarch

import android.app.Application
import ins1der.cleanarch.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.ERROR)
            androidContext(this@App)
            modules(listOf(
                networkModule,
                useCaseModule,
                activityModule,
                localStorageModule,
                repositoryModule
            ))
        }
    }
}

/*
paging
separate all layers
make boundaries
use https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-result/index.html instead my Result class

 */