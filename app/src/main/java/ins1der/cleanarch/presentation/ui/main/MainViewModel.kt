package ins1der.cleanarch.presentation.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ins1der.cleanarch.common.ResultType
import ins1der.cleanarch.domain.usecases.GetEmployeeUseCase
import ins1der.cleanarch.presentation.ui.models.EmployeeUI
import ins1der.cleanarch.presentation.ui.models.mapToUI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val getEmployeeUseCase: GetEmployeeUseCase): ViewModel() {

    private val _employees = MutableLiveData<List<EmployeeUI>?>()
    val employees: LiveData<List<EmployeeUI>?> = _employees

    fun loadEmployees() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = getEmployeeUseCase.execute()
                if (res.resultType == ResultType.SUCCESS) {
                    _employees.postValue(res.data?.map { it.mapToUI() })
                }
            }
        }
    }
}