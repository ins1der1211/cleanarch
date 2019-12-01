package ins1der.cleanarch

import android.app.Application
import ins1der.cleanarch.presentation.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
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