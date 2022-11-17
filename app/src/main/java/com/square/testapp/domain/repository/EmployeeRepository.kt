package com.square.testapp.domain.repository

import com.square.testapp.domain.employee.EmployeesData
import com.square.testapp.domain.util.Resource

interface EmployeeRepository {
    suspend fun getEmployeeData(): Resource<EmployeesData>
}