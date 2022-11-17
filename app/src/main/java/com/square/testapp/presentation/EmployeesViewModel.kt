package com.square.testapp.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.testapp.domain.repository.EmployeeRepository
import com.square.testapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {


    var state by mutableStateOf(EmployeesState())
        private set

    fun loadEmployees() {
        viewModelScope.launch {
            state = state.copy(isLoading = true,error = null)

            when( val result = repository.getEmployeeData()) {

                is Resource.Success -> {
                    state = state.copy(
                        employeesData = result.data,
                        isLoading = false,
                        error = null
                    )
                }
                is Resource.Error -> {
                    state = state.copy(
                        employeesData = null,
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }
}