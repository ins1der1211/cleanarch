package ins1der.cleanarch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import ins1der.cleanarch.presentation.ui.models.PlanetUI
import ins1der.cleanarch.presentation.ui.models.mapToUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class MainViewModel(private val getPlanetsUseCase: GetPlanetsUseCase): ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getPlanets() {
        Timber.d("getPlanets called")
        viewModelScope.launch(
            block = {
                withContext(Dispatchers.IO) {
                    val res = getPlanetsUseCase.execute()
                    if (res.isSuccess) {
                        _viewState.postValue(ViewState(planets = res.getOrNull()?.map { it.mapToUI() }))
                    } else {
                        _viewState.postValue(ViewState(error = res.exceptionOrNull()?.message))
                    }
                }
            },
            context = CoroutineExceptionHandler { ctx, t ->
                _viewState.postValue(ViewState(error = t.message))
            })
    }
}

data class ViewState(
    val planets: List<PlanetUI>? = null,
    val error: String? = null
)