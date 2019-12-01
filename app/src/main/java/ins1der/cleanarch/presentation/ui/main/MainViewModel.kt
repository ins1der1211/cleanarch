package ins1der.cleanarch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ins1der.cleanarch.common.ResultType
import ins1der.cleanarch.domain.usecases.GetPlanetsUseCase
import ins1der.cleanarch.presentation.ui.models.PlanetUI
import ins1der.cleanarch.presentation.ui.models.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getPlanetsUseCase: GetPlanetsUseCase): ViewModel() {

    private val _planets = MutableLiveData<List<PlanetUI>?>()
    val planets: LiveData<List<PlanetUI>?> = _planets

    fun getPlanets() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = getPlanetsUseCase.execute()
                if (res.resultType == ResultType.SUCCESS) {
                    _planets.postValue(res.data?.map { it.mapToUI() })
                }
            }
        }
    }
}