package ins1der.cleanarch.presentation.ui.second

import androidx.lifecycle.*
import ins1der.cleanarch.data.models.Listing
import ins1der.cleanarch.domain.usecases.GetPeopleUseCase
import ins1der.cleanarch.presentation.ui.models.PersonUI
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondViewModel(private val getPeopleUseCase: GetPeopleUseCase): ViewModel() {

    private val _listing = MutableLiveData<Listing<PersonUI>>()
    val peopleList = Transformations.switchMap(_listing) {
        it.pagedList
    }
    val networkState = Transformations.switchMap(_listing) {
        it.networkState
    }
    private val _viewState = MutableLiveData<ViewState>()
    val viewState: LiveData<ViewState> = _viewState

    fun getPeople() {
        viewModelScope.launch(
            block = {
                _listing.value = getPeopleUseCase.execute<Listing<PersonUI>>(10, 10).getOrThrow()
            },
            context = CoroutineExceptionHandler { _, t ->
                _viewState.postValue(Error(t.message))
            }
        )
    }

    fun refresh() {
        _listing.value?.refresh?.invoke()
    }

    fun retry() {
        _listing.value?.retry?.invoke()
    }
}

sealed class ViewState
data class Error(val txt: String? = null): ViewState()