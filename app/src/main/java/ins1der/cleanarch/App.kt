package ins1der.cleanarch

import android.app.Application
import ins1der.cleanarch.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()
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

prefs
paging
navigation
files


 */