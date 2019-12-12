package ins1der.cleanarch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ins1der.cleanarch.domain.usecases.ChangeRandomPlanetUseCase
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import ins1der.cleanarch.presentation.ui.models.PlanetUI
import ins1der.cleanarch.presentation.ui.models.mapToUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel(private val getPlanetsUseCase: GetPlanetsUseCase,
                    private val changeRandomPlanetUseCase: ChangeRandomPlanetUseCase): ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    val planets = getPlanetsUseCase.planetsLive

    fun loadPlanets(update: Boolean = false) {
        Timber.d("loadPlanets($update) called")
        viewModelScope.launch(
            block = {
                withContext(Dispatchers.IO) {
                    val res = getPlanetsUseCase.execute(update)
                    res.exceptionOrNull()?.let {
                        _viewState.postValue(ViewState(error = it.message))
                    }
                }
            },
            context = CoroutineExceptionHandler { ctx, t ->
                _viewState.postValue(ViewState(error = t.message))
            })
    }

    fun changeRandomPlanet() {
        viewModelScope.launch(
            block = {
                withContext(Dispatchers.IO) {
                    changeRandomPlanetUseCase.execute()
                }
            },
            context = CoroutineExceptionHandler { _, t ->
                _viewState.postValue(ViewState(error = t.message))
            }
        )
    }
}

data class ViewState(
    val error: String? = null
)