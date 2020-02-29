package ins1der.gifmaker.presentation.di

import ins1der.gifmaker.presentation.ui.main.MainViewModel
import ins1der.gifmaker.presentation.ui.second.SecondViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val activityModule = module {

    viewModel { MainViewModel(get(), get()) }

    viewModel { SecondViewModel(get()) }
}