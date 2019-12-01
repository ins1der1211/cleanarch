package ins1der.cleanarch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ins1der.cleanarch.common.ResultType
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import ins1der.cleanarch.presentation.ui.models.PlanetUI
import ins1der.cleanarch.presentation.ui.models.mapToUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getPlanetsUseCase: GetPlanetsUseCase): ViewModel() {

    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getPlanets() {
        viewModelScope.launch(
            block = {
                withContext(Dispatchers.IO) {
                    val res = getPlanetsUseCase.execute()
                    if (res.resultType == ResultType.SUCCESS) {
                        _viewState.postValue(ViewState(planets = res.data?.map { it.mapToUI() }))
                    } else {
                        _viewState.postValue(ViewState(error = res.error?.message))
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