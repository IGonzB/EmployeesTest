package com.square.testapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.square.testapp.domain.employee.Employee
import com.square.testapp.domain.repository.EmployeeRepository
import com.square.testapp.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class EmployeesViewModel @Inject constructor(
    private val repository: EmployeeRepository
) : ViewModel() {


    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _currentTime = MutableStateFlow(Date())
    val currentTime = _currentTime.asStateFlow()

    private val _items = MutableStateFlow(List(size = 20) { Employee() })
    val items = _items.asStateFlow()

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadEmployees()
    }

    fun refresh() = loadEmployees()

    private fun loadEmployees() {
        viewModelScope.launch {
            _isRefreshing.update { true }
            _isLoading.update { true }
            _currentTime.value = Date()

            when (val result = repository.getEmployeeData()) {

                is Resource.Success -> {
                    _items.value = result.data!!.employees
                }
                is Resource.Error -> {
                    _items.value = emptyList()
                }
            }
            _isRefreshing.update { false }
            _isLoading.update { false }
        }
    }
}