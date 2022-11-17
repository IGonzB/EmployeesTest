package com.square.testapp.data.remote

import retrofit2.http.GET

interface EmployeeApi {

    @GET("sq-mobile-interview/employees.json")
    suspend fun getEmployeesData(): EmployeeResponse

    @GET("sq-mobile-interview/employees_malformed.json")
    suspend fun getEmployeesDataMalformed(): EmployeeResponse

    @GET("sq-mobile-interview/employees_empty.json")
    suspend fun getEmployeesDataEmpty(): EmployeeResponse
}