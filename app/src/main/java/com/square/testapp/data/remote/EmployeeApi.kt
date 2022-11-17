package com.square.testapp.data.remote

import retrofit2.http.GET

interface EmployeeApi {

    @GET("sq-mobile-interview/employees.json")
    suspend fun getEmployeesData(): EmployeeResponse
}