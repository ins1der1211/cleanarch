package ins1der.cleanarch.presentation.di

import ins1der.cleanarch.presentation.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {

    viewModel { MainViewModel(get()) }
}