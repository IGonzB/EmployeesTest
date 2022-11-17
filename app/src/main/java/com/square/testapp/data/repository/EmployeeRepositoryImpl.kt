package com.square.testapp.data.repository

import com.square.testapp.data.mappers.toEmployeesInfo
import com.square.testapp.data.remote.EmployeeApi
import com.square.testapp.domain.employee.EmployeesData
import com.square.testapp.domain.repository.EmployeeRepository
import com.square.testapp.domain.util.Resource
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor(
    private val api: EmployeeApi
) : EmployeeRepository {
    override suspend fun getEmployeeData(): Resource<EmployeesData> {

        return try {
//            when ((1..3).random()) {
            when (1) {

                1 -> {
                    Resource.Success(data = api.getEmployeesData().toEmployeesInfo())
                }
                2 -> {
                    Resource.Success(data = api.getEmployeesDataMalformed().toEmployeesInfo())
                }
                3 -> {
                    Resource.Success(data = api.getEmployeesDataEmpty().toEmployeesInfo())
                }
                else -> {
                    Resource.Error("An unknown error occurred.")
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()

            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}

