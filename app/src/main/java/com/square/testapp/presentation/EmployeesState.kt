package com.square.testapp.presentation

import com.square.testapp.domain.employee.EmployeesData

data class EmployeesState(
    val employeesData: EmployeesData? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
